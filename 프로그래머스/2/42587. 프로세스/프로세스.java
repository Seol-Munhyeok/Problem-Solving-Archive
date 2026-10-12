import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < priorities.length; i++) {
            q.offer(i);
        }
        
        int count = 0;
        
        while (!q.isEmpty()) {
            int x = q.poll();
            boolean hasHigherPriority = false;

            for (int i : q) {
                if (priorities[i] > priorities[x]) {
                    hasHigherPriority = true;
                    break;
                }
            }
            
            if (hasHigherPriority) {
                q.offer(x);
                continue;
            }
            
            count++;
            
            if (x == location) {
                return count;
            }
        }
        
        return -1;
    }
}