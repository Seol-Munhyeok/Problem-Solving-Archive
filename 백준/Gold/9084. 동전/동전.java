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
			int[] dp = new int[M + 1];  // 현재까지 고려한 동전들로 i 원을 만드는 방법 수
			dp[0] = 1;
			
			for (int i = 0; i < N; i++) {
				for (int j = coin[i]; j <= M; j++) {
					dp[j] += dp[j - coin[i]];
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		
		System.out.println(sb);
	}
}
