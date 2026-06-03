import java.util.*;
/**
Return 당첨에서 제외되어야 할 제재 아이디 목록의 경우의 수 (나열 순서 고려 X)
1 <= user_id.length <= 8
1 <= user_id[0].length <= 8 -> 각각은 서로 중복되지 않음
user_id = ["frodo", "fradi", "crodo", "abc123", "frodoc"]

각 banned_id마다 매핑되는 user_id를 저장
    "fr*d*" -> "frodo", "fradi"
    "*rodo" -> "frodo", "crodo"
    "******" -> "abc123", "frodoc"
    "******" -> "abc123", "frodoc"
가능한 user_id에서 중복을 포함하지 않도록 dfs (각 행에서 하나씩 제거)

최종적으로 정렬된 순서로 붙여서 Set에 담아서 순서를 고려하지 않는 경우의 수를 구한다.
*/
class Solution {
    
    int N;
    List<String>[] lst;
    List<String> selected;
    String[] user_id;
    Set<String> used;
    Set<Set<String>> answer;
    
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        N = banned_id.length;
        lst = new List[N];

        int idx = 0;
        for (String id : banned_id) {
            lst[idx++] = findUser(id);
        }
        
        selected = new ArrayList<>();
        used = new HashSet<>();
        answer = new HashSet<>();
        dfs(0);
        return answer.size();
    }
    
    private void dfs(int idx) {

        if (idx == N) {   
            answer.add(new HashSet<>(selected));
            return;
        }
        
        for (String user : lst[idx]) {
            if (used.contains(user)) continue;
            
            selected.add(user);
            used.add(user);
            
            dfs(idx + 1);
            
            selected.remove(selected.size() - 1);
            used.remove(user);
        }
        
    }
    
    private List<String> findUser(String id) {
        List<String> result = new ArrayList<>();
        for (String user : user_id) {
            if (user.length() != id.length()) continue;
            boolean ok = true;
            for (int i = 0; i < user.length(); i++) {
                if (id.charAt(i) == '*') continue;
                if (user.charAt(i) != id.charAt(i)) {
                    ok = false;
                    break;
                }
            }
            if (ok) result.add(user);
        }
        
        return result;
    }
}