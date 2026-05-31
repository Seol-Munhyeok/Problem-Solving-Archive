class Solution {
    int MOD = 20170805;
    int[][][] dp;
    
    public int solution(int m, int n, int[][] cityMap) {
        // dp[i][j][0] = 위에서 내려와서 (i, j)에 도착한 경우의 수
        // dp[i][j][1] = 왼쪽에서 내려와서 (i, j)에 도착한 경우의 수
        dp = new int[m][n][2];
        
        if (m == 1 && n == 1) return 1;  // 특수 케이스 예외 처리
        
        // 1행 및 1열 초기화
        for (int i = 0; i < m; i++) {
            if (cityMap[i][0] == 1) break;
            dp[i][0][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (cityMap[0][i] == 1) break;
            dp[0][i][1] = 1;
        }
        
        // dp 테이블 채우기
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i][j] == 1) continue;
                
                // 위에서 내려오는 경우
                int prev = cityMap[i - 1][j];
                if (prev == 0) {   // 이전 칸이 자유 통행
                    dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                } else if (prev == 2) {   // 이전 칸이 회전 금지
                    dp[i][j][0] = dp[i - 1][j][0];
                } else {  // 이전 칸이 통행 금지
                    dp[i][j][0] = 0;
                }
                
                // 왼쪽에서 내려오는 경우
                prev = cityMap[i][j - 1];
                if (prev == 0) {   // 이전 칸이 자유 통행
                    dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                } else if (prev == 2) {   // 이전 칸이 회전 금지
                    dp[i][j][1] = dp[i][j - 1][1];
                } else {  // 이전 칸이 통행 금지
                    dp[i][j][1] = 0;
                }  
            }
        }      
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}