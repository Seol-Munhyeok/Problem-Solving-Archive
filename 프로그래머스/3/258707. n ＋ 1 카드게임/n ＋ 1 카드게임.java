import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        boolean[] hasCard = new boolean[n + 1];
        for (int i = 0; i < n / 3; i++) {
            hasCard[cards[i]] = true;
        }
        
        boolean[] extra = new boolean[n + 1];
        int round = 1;
        for (int i = n / 3; i < n; i += 2) {
            // 추가 카드 공개
            extra[cards[i]] = true;
            extra[cards[i + 1]] = true;
            
            // 1. 추가 카드를 받지 않고 쌍이 존재하는지 확인
            boolean found = false;
            for (int x = 1; x <= n; x++) {
                if (hasCard[x] && hasCard[n + 1 - x]) {
                    hasCard[x] = false;
                    hasCard[n + 1 - x] = false;
                    found = true;
                    break;
                }
            }
            // 2. 코인 1개 사용
            if (!found && coin >= 1) {
                for (int x = 1; x <= n; x++) {
                    if (extra[x] && hasCard[n + 1 - x]) {
                        extra[x] = false;
                        hasCard[n + 1 - x] = false;
                        found = true;
                        coin--;
                        break;
                    }
                }
            }
            
            // 3. 코인 2개 사용
            if (!found && coin >= 2) {
                for (int x = 1; x <= n; x++) {
                    if (extra[x] && extra[n + 1 - x]) {
                        extra[x] = false;
                        extra[n + 1 - x] = false;
                        found = true;
                        coin -= 2;
                        break;
                    }
                }
            }
            
            if (!found) {
                break;
            }
            
            round++;
        }
          
        return round;
    }
}