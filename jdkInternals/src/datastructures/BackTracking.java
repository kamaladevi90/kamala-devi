package datastructures;

import java.util.ArrayList;
import java.util.List;
/*
 * n = 3 -> [((())), (()()), (())(), ()(()), ()()()]
n = 1 -> [()]
 */

public class BackTracking {

    public static void main(String[] args) {
    	Solution1 solution = new Solution1();

        System.out.println("n = 3 -> " + solution.generateParenthesis(5));
        System.out.println("n = 1 -> " + solution.generateParenthesis(1));
    }
}

class Solution1 {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result,
                           StringBuilder current,
                           int open,
                           int close,
                           int max) {

        // Base condition: length reached 2 * n
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // Add '(' if we still can
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // undo
        }

        // Add ')' only if it keeps parentheses valid
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // undo
        }
    }
}

