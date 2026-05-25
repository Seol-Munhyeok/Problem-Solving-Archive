import java.util.*;
/**
균형잡힌 괄호 문자열 : '(' 의 개수와 ')' 의 개수가 같다
올바른 괄호 문자열 : '('와 ')'의 괄호의 짝도 모두 맞음

2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
-> 문자열 p를 이루는 '(' 와 ')' 의 개수는 항상 같으므로, 왼쪽부터 보면서 처음으로 나오는 균형잡힌 문자열이 u, 나머지가 v가 됨
*/
class Solution {
    
    public String solution(String p) {
        if (p.isEmpty()) return "";
        
        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        int left = 0;   // '(' 개수
        int right = 0;  // ')' 개수
        String u = "", v = "";
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            if (ch == '(') left++;
            else right++;
            
            if (left == right) {
                u = p.substring(0, i + 1);
                v = p.substring(i + 1);
                break;
            } 
        }
        
        // 3. 재귀적으로 수행
        if (correct(u)) return u + solution(v);
        else {  
            String s1 = "(" + solution(v) + ")";
            String s2 = reverse(u.substring(1, u.length() - 1));
            return s1 + s2;
        }
    }
    
    private boolean correct(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.push('(');
            } else {
                if (stk.isEmpty()) return false;
                stk.pop();
            }
        }
        return stk.isEmpty();
    }
    
    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                sb.append(")");
            } else {
                sb.append("(");
            }
        }
        return sb.toString();
    }
    
}