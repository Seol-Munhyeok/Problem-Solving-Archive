class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n][m];
        
        // 웅덩이 위치 기록
        boolean[][] impossible = new boolean[n][m];
        for (int[] pos : puddles) {
            impossible[pos[1] - 1][pos[0] - 1] = true;
        }
        
        // 0행, 0열 이동 기록
        for (int i = 0; i < n; i++) {
            if (impossible[i][0]) break;
            dp[i][0] = 1;
        }
        
        for (int i = 0; i < m; i++) {
            if (impossible[0][i]) break;
            dp[0][i] = 1;
        }
        
        // 1행 1열 부터 경우의 수 계산
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (impossible[i][j]) continue;
                
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1_000_000_007;
            }
        }
        
        return dp[n - 1][m - 1];
    }
}