import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        for (String gem : gems) {
            map.put(gem, map.getOrDefault(gem, 0) + 1);
        }
        int n = map.size();
        
        map = new HashMap<>();
        int[] answer = new int[2];
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < gems.length; right++) {
            String gem = gems[right];
            map.put(gem, map.getOrDefault(gem, 0) + 1);
            while (map.size() == n) {
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }
                String removed = gems[left];
                left++;
                map.put(removed, map.get(removed) - 1);
                if (map.get(removed) == 0) {
                    map.remove(removed);
                }
            }
        }
        return answer;
    }
}