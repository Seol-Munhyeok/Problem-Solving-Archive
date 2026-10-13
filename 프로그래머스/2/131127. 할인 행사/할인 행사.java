import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < discount.length; i++) {
            String item = discount[i];
            map.put(item, map.getOrDefault(item, 0) + 1);
            
            if (i >= 10) {
                String left = discount[i - 10];
                map.put(left, map.getOrDefault(left, 0) - 1);
                
                if (map.get(left) == 0) {
                    map.remove(left);
                }
            }
            
            boolean possible = true;
            for (int j = 0; j < want.length; j++) {
                if (map.getOrDefault(want[j], -1) != number[j]) {
                    possible = false;
                    break;
                }
            }
            
            if (possible) {
                answer++;
            }
            
        }
        return answer;
    }
}