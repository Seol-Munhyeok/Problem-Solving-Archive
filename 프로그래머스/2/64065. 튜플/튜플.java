/**
(a1, a2, a3, ..., an) 를
{{a1}, {a1, a2}, {a1, a2, a3}, {a1, a2, a3, a4}, ... {a1, a2, a3, a4, ..., an}} 로 표현
    각 집합의 원소에 대해서는 순서를 고려하지 않음
Return 특정 튜플을 표현하는 집합이 담긴 문자열 s가 매개변수로 주어질 때, s가 표현하는 튜플
집합은 순서가 없고, 튜플은 순서가 있음
    순서가 없는 나열에서 규칙을 기반으로 튜플의 순서를 찾아야 함
"{{4,2,3},{3},{2,3,4,1},{2,3}}"
-> 배열의 길이 기준으로 정렬 "{3}, {2,3}, {4,2,3}, {2,3,4,1}"
순서대로 3 -> {2,3} 중 3은 있으니까 2붙이고, 4붙이고, 1붙이고...
중복이 없으니까 한 번 순회하면서 있는 원소는 무시하고 새로운 원소만 계속 추가하면 됨
*/

import java.util.*;

class Solution {
    public int[] solution(String s) {
        List<List<Integer>> lst = parseSet(s);
        lst.sort((a, b) -> Integer.compare(a.size(), b.size()));
        Set<Integer> used = new HashSet<>();
        
        int[] answer = new int[lst.size()];
        int idx = 0;
        for (List<Integer> l : lst) {
            for (int num : l) {
                if (!used.contains(num)) {
                    answer[idx++] = num;
                    used.add(num);
                }
            }
        }
        
        return answer;
    }
    
    private List<List<Integer>> parseSet(String s) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> lst = new ArrayList<>();
        int num = 0;
        boolean inSet = false;
        
        for (int i = 1; i < s.length() - 1; i++) {
            char ch = s.charAt(i);
            if (ch == '{') {
                inSet = true;
                lst = new ArrayList<>();
            } else if (ch >= '0' && ch <= '9') {
                num = num * 10 + (ch - '0');
            } else if (inSet && ch == ',') {
                lst.add(num);
                num = 0;
            } else if (ch == '}') {
                lst.add(num);
                result.add(lst);
                inSet = false;
                num = 0;
            }
        }

        return result;
    }
}