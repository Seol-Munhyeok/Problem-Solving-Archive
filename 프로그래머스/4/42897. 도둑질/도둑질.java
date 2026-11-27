class Solution {
    public int solution(int[] money) {
        int n = money.length;
        return Math.max(solve(money, 1, n - 1), solve(money, 0, n - 2));
    }
    
    // start부터 end까지의 최대 금액 계산
    private int solve(int[] money, int start, int end) {
        int n = money.length;
        // dp[i] = 0번째 집부터 i번째 집까지 털어서 얻을 수 있는 최대 금액
        int[] dp = new int[n + 1];
        dp[start] = money[start];
        dp[start + 1] = Math.max(money[start], money[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int rob = money[i] + dp[i - 2];
            int notRob = dp[i - 1];
            dp[i] = Math.max(rob, notRob);
        }
        return dp[end];
    }
}