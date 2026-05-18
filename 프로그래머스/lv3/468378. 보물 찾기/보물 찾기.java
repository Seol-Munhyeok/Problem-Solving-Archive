import java.util.*;
import java.util.function.Function;
/**
w * h 격자 안에 단 하나의 보물 (w <= 200, h <= 100,000)

excavate(col) 함수를 호출
    col 열을 가능한 최대 깊이 만큼 땅을 팜
        col 열에 보물이 있으면 -> return 0
        col 열 기준 왼쪽에 보물이 있으면 -> return -1
        col 열 기준 오른쪽에 보물이 있으면 -> return 1
    땅을 팔 때마다 굴착 가능한 깊이만큼 비용이 발생

excavate 함수를 호출해 보물을 찾아 발굴하고, 보물이 있었던 열을 return
    excavate 함수를 호출해 한 번이상 0을 return 받고, 총 비용이 money 이하여야 함
    money 이하로 보물을 찾을 수 있음을 보장함.
*/

class Solution {
    int w;
    long[] d;
    long[][] cost;
    int[][] pick;
    
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        w = depth.length;
        d = new long[w + 2];  // 1-based로 변경
        for (int i = 1; i <= w; i++) {
            d[i] = depth[i - 1];
        }
        // cost[L][R] = 보물이  L~R  구간에  있을  때,  보물을  찾기  위한  최소  비용
        cost = new long[w + 2][w + 2];
        // pick[L][R] = 보물이  L~R  구간에  있을  때,  굴착해야  하는  열
        pick = new int[w + 2][w + 2];
        
        fillDpTable();

        // 실제 탐색 시작
        int L = 1, R = w;
        while (true) {
            int col = pick[L][R];
            int result = excavate.apply(col);
            
            if (result == 0) return col;
            else if (result == -1) R = col - 1;
            else L = col + 1;
        }
    }
    
    private void fillDpTable() {
        // 모든 [L, R] 구간에 대해 확인
        for (int len = 1; len <= w; len++) {
            for (int L = 1; L + len - 1 <= w; L++) {
                int R = L + len - 1;
                
                cost[L][R] = Long.MAX_VALUE;
                // i 열을 굴착할 경우
                for (int i = L; i <= R; i++) {
                    long leftCost = cost[L][i - 1];
                    long rightCost = cost[i + 1][R];
                    
                    long worst = d[i] + Math.max(leftCost, rightCost);
                    
                    if (worst < cost[L][R]) {
                        cost[L][R] = worst;
                        pick[L][R] = i;
                    }
                }
            }
        }
    }
}