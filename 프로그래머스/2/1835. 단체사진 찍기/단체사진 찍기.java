import java.util.*;
/**
8명 순열 8! = 40320
생성되는 모든 순열에 대해 유효한 순열인지 확인 최대 100회
모든 순열을 생성하고 유효한 순열인지 확인
*/
class Solution {
    
    Map<Character, Integer> pos;
    char[] ch = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    boolean[] visited;
    String[] data;
    int answer;

    public int solution(int n, String[] data) {
        pos = new HashMap<>();
        visited = new boolean[8];
        this.data = data;
        
        answer = 0;
        dfs(0);
        return answer;
    }
    
    private void dfs(int depth) {

        if (depth == 8) {
            if (checkPossible()) answer++;
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            pos.put(ch[i], depth);
            
            dfs(depth + 1);
            
            visited[i] = false;
        }
    }
    
    private boolean checkPossible() {
        for (String cmd : data) {
            int pos1 = pos.get(cmd.charAt(0)) - '0';  // char -> int 변환 실수 조심!
            int pos2 = pos.get(cmd.charAt(2)) - '0';
            char cond = cmd.charAt(3);
            int num = cmd.charAt(4) - '0';
            
            if (cond == '=') {
                if (Math.abs(pos1 - pos2) - 1 != num) {
                    return false;
                }
            } else if (cond == '>') {
                if (Math.abs(pos1 - pos2) - 1 <= num) {
                    return false;
                }
            } else {
                if (Math.abs(pos1 - pos2) - 1 >= num) {
                    return false;
                }
            }
        }        
        return true;
    }
}