import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        Deque<Integer> stk = new ArrayDeque<>();
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            while (!stk.isEmpty() && prices[i] < prices[stk.peek()]) {
                answer[stk.peek()] = i - stk.peek();
                stk.pop();
            }
            stk.push(i);      
        }
        while (!stk.isEmpty()) {
            int top = stk.pop();
            answer[top] = n - 1 - top;
        }
        return answer;
    }
}