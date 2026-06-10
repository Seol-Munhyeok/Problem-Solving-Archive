/**
가입자 최대화 -> 그 후 판매액 최대화
n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매
    할인율은 10%, 20%, 30%, 40% 중 하나
    
사용자는 일정 비율 이상 할인하는 이모티콘을 모두 구매
구매 비용의 합이 일정 가격 이상이 된다면 구매를 모두 취소하고 서비스에 가입

1 <= users.length <= 100
1 <= emoticons.length <= 7
7개의 이모티콘 마다 4가지 할인율 중 하나를 선택 -> 4^7 = 16384
각 경우 마다 실제로 가입자수와 매출액을 완전 탐색
*/

import java.util.*;

class Solution {
    
    int M;
    int[] emoticons;
    int[][] users;
    int[] discountRate = {10, 20, 30, 40};
    int[] candRate, candDiscountPrice;
    int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        M = emoticons.length;
        candRate = new int[M];
        candDiscountPrice = new int[M];
        
        answer = new int[] {0, 0};  // {가입 수, 매출액}
        dfs(0);
        return answer;
    }
    
    private void dfs(int idx) {
        if (idx == M) {
            int[] result = getResult();  // {가입 수, 매출액}
            answer = better(answer, result);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            candRate[idx] = discountRate[i];
            candDiscountPrice[idx] = emoticons[idx] * (100 - discountRate[i]) / 100;
            dfs(idx + 1);
        }
    }
    
    private int[] getResult() {
        int registerCount = 0, profit = 0;
        // 모든 사용자 탐색
        for (int[] user : users) {
            int rate = user[0], priceStandard = user[1];
            int tempPrice = 0;
            boolean register = false;
            // 모든 이모티콘 탐색
            for (int i = 0; i < M; i++) {
                if (candRate[i] >= rate) {
                    tempPrice += candDiscountPrice[i];
                    if (tempPrice >= priceStandard) {
                        register = true;
                        registerCount++;
                        break;
                    }
                }
            }
            // 서비스 미가입 시 판매액 증가 
            if (!register) {
                profit += tempPrice;
            }
        }
        
        return new int[] {registerCount, profit};
    }
   
    private int[] better(int[] a, int[] b) {
        if (b[0] > a[0]) return b;
        if (b[0] == a[0] && b[1] > a[1]) return b;
        return a;
    }
}