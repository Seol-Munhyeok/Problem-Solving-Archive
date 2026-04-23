import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static String next() throws Exception {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}
	
	static int nextInt() throws Exception {
		return Integer.parseInt(next());
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = nextInt();
			int[] coin = new int[N];
			
			for (int i = 0; i < N; i++) {
				coin[i] = nextInt();
			}
			
			int M = nextInt();  // 만들어야 할 금액
			int[][] dp = new int[N][M + 1];  // dp[i][j] = i번째 동전까지 고려할 때 j원을 만드는 경우의 수
			
			for (int i = 0; i < N; i++) {
                dp[i][0] = 1;
            }
			
			for (int i = 0; i < N; i++) {
				for (int j = 1; j <= M; j++) {
					dp[i][j] = 0;
					if (i > 0) {
						dp[i][j] += dp[i - 1][j];  // i번째 동전 사용하지 않는 경우
					}
					if (j - coin[i] >= 0) {
						dp[i][j] += dp[i][j - coin[i]];  // i번째 동전을 사용하는 경우
					}
				}
			}
			
			sb.append(dp[N - 1][M]).append("\n");
		}
		
		System.out.println(sb);
	}
}
