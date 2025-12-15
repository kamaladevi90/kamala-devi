package com.nokia;



import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final StringRedisTemplate redisTemplate;
    private final DefaultRedisScript<Long> script;

    public InventoryService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;

        this.script = new DefaultRedisScript<>();
        this.script.setResultType(Long.class);
        this.script.setScriptText("""
            local stock = tonumber(redis.call('GET', KEYS[1]))
            if stock and stock > 0 then
                redis.call('DECR', KEYS[1])
                return 1
            end
            return 0
        """);
    }

    public boolean reserve(Long productId) {
        String key = "inventory:product:" + productId;

        Long result = redisTemplate.execute(
                script,
                List.of(key)
        );
        return result != null && result == 1;
    }

    public void rollback(Long productId) {
        redisTemplate.opsForValue()
                .increment("inventory:product:" + productId);
    }
}
