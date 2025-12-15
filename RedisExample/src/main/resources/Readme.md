# Redis Saga Demo (Spring Boot + Redis + H2)

This repository contains a **complete end-to-end microservice example** demonstrating:

- Java 17
- Spring Boot 3.x
- Redis for **atomic operations**
- H2 in-memory database (default, recreated on every restart)
- **Saga pattern (orchestration-based)** with compensation logic
- Safe handling of **multiple concurrent orders** without overselling

---

## 📌 Business Use Case

### Order Placement with Inventory Control

- Multiple users place orders simultaneously
- Inventory is limited
- Redis performs atomic inventory reservation
- Orders are persisted in database
- If database transaction fails, inventory is **restored automatically**

This guarantees **eventual consistency** without distributed transactions.

---

## 🏗 System Architecture

Client
↓
OrderController (REST API)
↓
OrderSagaService (Saga Orchestrator)
↓
InventoryService (Redis + Lua)
↓
Redis (Atomic Inventory Reservation)
↓
H2 Database (Order Persistence)
↓
Compensation on Failure

yaml
Copy code

---

## 🧠 Key Concepts Demonstrated

- Redis atomic operations using Lua scripts
- Saga pattern (orchestration-based)
- Compensation logic
- Eventual consistency
- High concurrency handling
- Stateless microservice design

---

## 🛠 Prerequisites

- Java 17+
- Maven 3.8+
- Docker (recommended for Redis)

---

## 🚀 Step 1: Start Redis

### Using Docker (Recommended)

```bash
docker run -d --name redis -p 6379:6379 redis:7
Verify Redis is running:

bash
Copy code
docker exec -it redis redis-cli
PING
# Expected: PONG
🚀 Step 2: Build and Run the Application
bash
Copy code
mvn clean install
mvn spring-boot:run
Application URL:

arduino
Copy code
http://localhost:8080
🗄 Step 3: Initialize Inventory in Redis
bash
Copy code
redis-cli
SET inventory:product:101 3
This sets the available inventory for product 101 to 3 units.

📡 REST API
Place Order
Endpoint

bash
Copy code
POST /orders/{productId}
Example Request

bash
Copy code
curl -X POST http://localhost:8080/orders/101
Possible Responses

✅ Order placed successfully

❌ Out of stock

❌ Order failed, inventory restored

🔁 Concurrent Order Simulation
bash
Copy code
for i in {1..10}; do
  curl -X POST http://localhost:8080/orders/101 &
done
Expected Behavior
Only 3 requests succeed

Remaining requests fail gracefully

Inventory is never oversold

🧪 H2 Database
H2 Console
bash
Copy code
http://localhost:8080/h2-console
JDBC Configuration
yaml
Copy code
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (empty)
⚠️ Note: H2 is in-memory. Data is lost when the application restarts.

🔐 Saga Pattern – How It Works
Step-by-Step Flow
Client requests order creation

Redis Lua script atomically:

Checks stock

Decrements stock if available

Order is saved in H2 database (transactional)

If DB save fails:

Redis inventory is incremented back (compensation)

System reaches eventual consistency

⚙ Redis Atomic Operation (Concept)
Redis Lua script ensures check-and-decrement happens atomically:

lua
Copy code
if tonumber(redis.call('get', KEYS[1])) > 0 then
  return redis.call('decr', KEYS[1])
else
  return -1
end
⚙ Redis Key Design
Key	Description
inventory:product:{id}	Stores product stock count

📦 Project Structure
css
Copy code
redis-saga-demo
 └── src/main/java/com/example/demo
     ├── DemoApplication.java
     ├── config
     │    └── RedisConfig.java
     ├── controller
     │    └── OrderController.java
     ├── entity
     │    └── Order.java
     ├── repository
     │    └── OrderRepository.java
     ├── service
     │    ├── InventoryService.java
     │    └── OrderSagaService.java
 └── src/main/resources
     └── application.properties
 └── pom.xml
🎯 Why This Design Works
No database locks

No distributed transactions (XA)

Redis handles high concurrency efficiently

Microservice-friendly architecture

Horizontally scalable

🎤 Interview-Ready Explanation
“This service uses Redis as an atomic inventory gatekeeper and implements the Saga pattern to compensate inventory when the database transaction fails, ensuring data consistency without distributed transactions.”

🔮 Possible Enhancements
Event-driven Saga using Kafka

Redis Streams for workflow orchestration

Idempotency keys

Retry and dead-letter queues

Docker Compose for full stack setup

Observability (Prometheus + Grafana)

📄 License
This project is intended for learning and demonstration purposes only.

markdown
Copy code

---

### ✅ You’re all set
This **single README.md file** now contains **everything**.

If you want next:
- 📦 **Docker Compose file**
- 📘 **Swagger/OpenAPI config**
- 🎯 **Interview Q&A based on this project**
- 🧪 **JUnit + Testcontainers tests**

Just say the word 👍