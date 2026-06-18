import java.util.function.Function;
// 보물을 찾은 경우 0, 보물이 왼쪽 방향에 있다면 -1, 오른쪽 방향에 있다면 1
class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int w = depth.length;
        int[] d = new int[w + 1];  // depth를 1-based로 저장
        for (int i = 0; i < w; i++) {
            d[i + 1] = depth[i];
        }
        // cost[L][R] = L~R 구간에 보물이 있을 때, 보물을 찾기 위한 최소 비용
        long[][] cost = new long[w + 2][w + 2];  
        int[][] pick = new int[w + 2][w + 2];  // 해당 구간에서 최소 비용으로 찾기 위해 굴착할 열
        
        // 테이블 채우기        
        for (int len = 1; len <= w; len++) {
            for (int left = 1; left <= w - len + 1; left++) {
                int right = left + len - 1;
                
                cost[left][right] = Long.MAX_VALUE;
                for (int i = left; i <= right; i++) {
                    long worst = Math.max(cost[left][i - 1], cost[i + 1][right]);
                    if (cost[left][right] > d[i] + worst) {
                        cost[left][right] = d[i] + worst;
                        pick[left][right] = i;
                    }
                }
            }
        }
        
        // 굴착
        int left = 1, right = w;
        while (true) {
            int col = pick[left][right];
            int result = excavate.apply(col);
            if (result == 0) return col;
            else if (result == -1) {
                right = col - 1;
            } else {
                left = col + 1;
            }     
        }
    }
}