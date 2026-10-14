import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Map<Integer, Integer> right = new HashMap<>();
        Set<Integer> left = new HashSet<>();

        for (int t : topping) {
            right.put(t, right.getOrDefault(t, 0) + 1);
        }
        
        for (int t : topping) {
            int count = right.get(t);
            
            if (count == 1) {
                right.remove(t);
            } else {
                right.put(t, count - 1);
            }
            
            left.add(t);
            
            if (left.size() == right.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}