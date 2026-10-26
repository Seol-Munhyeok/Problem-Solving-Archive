import java.util.*;
class Solution {
    boolean[] visited;
    int[][] computers;
    int n;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        this.computers = computers;
        this.n = n;
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                dfs(i);
            }
        }
        return answer;
    }
    
    private void dfs(int now) {
        visited[now] = true;
        
        for (int next = 0; next < n; next++) {
            if (computers[now][next] == 1 && !visited[next]) {
                dfs(next);
            }
        }
    }
}