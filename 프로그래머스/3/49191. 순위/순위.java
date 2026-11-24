class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n + 1][n + 1];
        for (int[] result : results) {
            graph[result[0]][result[1]] = true;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }
        
        /*
0 1 0 0 1 
0 0 0 0 1 
0 1 0 0 1 
0 1 1 0 1 
0 0 0 0 0 
        */
        int[] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j]) {
                    count[i]++;
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[j][i]) {
                    count[i]++;
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (count[i] == n - 1) {
                answer++;
            }
        }
        return answer;
    }
}