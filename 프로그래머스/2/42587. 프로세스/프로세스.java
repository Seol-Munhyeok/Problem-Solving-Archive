import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Integer> q = new ArrayDeque<>();
        int n = priorities.length;
        boolean[] removed = new boolean[n];
        for (int i = 0; i < n; i++) {
            q.offer(i);
        }
        
        int count = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            boolean ok = true;
            // 우선순위가 더 높은 프로세스 확인
            for (int i = 0; i < n; i++) {
                if (x == i) continue;
                if (removed[i]) continue;
                if (priorities[x] < priorities[i]) {
                    ok = false;
                    q.offer(x);
                    break;
                }
            }
            if (ok) {
                removed[x] = true;
                count++;
            }
            
            if (ok && x == location) {
                return count;
            }
        }
        
        return -1;
    }
}