import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static final int INF = 1_000_000_000;
	static int N;
	static int[][] cost;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		cost = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cost[i][j] = sc.nextInt();
			}
		}
		
		dp = new int[1 << N];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int mask = 0; mask < (1 << N); mask++) {
			int k = Integer.bitCount(mask);  // 지금 일 배정할 사람 번호
			
			for (int next = 0; next < N; next++) {
				if ((mask & (1 << next)) == 0) {
					int nextMask = mask | (1 << next);
					dp[nextMask] = Math.min(dp[nextMask], dp[mask] + cost[k][next]);
				}
			}
		}
		
		System.out.println(dp[(1 << N) - 1]);
	}
}
