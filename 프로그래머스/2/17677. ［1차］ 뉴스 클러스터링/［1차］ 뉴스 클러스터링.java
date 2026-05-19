import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        
        Map<String, Integer> set1 = getMultiset(str1);
        Map<String, Integer> set2 = getMultiset(str2);
        
        int intersectCnt = getIntersectCount(set1, set2);
        int unionCnt = getUnionCount(set1, set2);
        System.out.println(intersectCnt);
        
        if (unionCnt == 0) return 65536;
        return (int)(((double)intersectCnt / unionCnt) * 65536);
    }
    
    private Map<String, Integer> getMultiset(String str) {
        Map<String, Integer> set = new HashMap<>();
        
        for (int i = 0; i < str.length() - 1; i++) {
            char a = str.charAt(i);
            char b = str.charAt(i + 1);
            if (a >= 'A' && a <= 'Z' && b >= 'A' && b <= 'Z') {
                String s = "" + a + b;
                set.put(s, set.getOrDefault(s, 0) + 1);
            }
        }
        return set;
    }
    
    private int getIntersectCount(Map<String, Integer> set1, Map<String, Integer> set2) {
        int res = 0;
        for (String key : set1.keySet()) {
            if (set2.containsKey(key)) {
                res += Math.min(set1.get(key), set2.get(key));
            }
        }
        return res;
    }
    
    private int getUnionCount(Map<String, Integer> set1, Map<String, Integer> set2) {
        int res = 0;
        for (String key : set1.keySet()) {
            if (set2.containsKey(key)) {
                res += Math.max(set1.get(key), set2.get(key));
            } else {
                res += set1.get(key);
            }
        }
        for (String key : set2.keySet()) {
            if (!set1.containsKey(key)) {
                res += set2.get(key);
            } 
        }
        return res;
    }
}