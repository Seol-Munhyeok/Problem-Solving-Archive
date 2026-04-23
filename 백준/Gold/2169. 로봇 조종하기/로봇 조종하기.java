import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
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
		
		int N = nextInt(); int M = nextInt();
		int[][] arr = new int[N][M];
		int[][] dp = new int[N][M];
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = nextInt();
			}
		}
		
		// 0행 초기화
		dp[0][0] = arr[0][0];
		for (int i = 1; i < M; i++) {
			dp[0][i] = dp[0][i - 1] + arr[0][i];
		}

		
		int[] leftOrder = new int[M];
		int[] rightOrder = new int[M];
		for (int i = 1; i < N; i++) {
			// 왼쪽 -> 오른쪽 확인
			leftOrder[0] = dp[i - 1][0] + arr[i][0]; 
			for (int j = 1; j < M; j++) {
				leftOrder[j] = Math.max(dp[i - 1][j], leftOrder[j - 1]) + arr[i][j];		
			}
			// 오른쪽 -> 왼쪽 확인
			rightOrder[M - 1] = dp[i - 1][M - 1] + arr[i][M - 1];
			for (int j = M - 2; j >= 0; j--) {
				rightOrder[j] = Math.max(dp[i - 1][j], rightOrder[j + 1]) + arr[i][j];		
			}
			// 두 방향 중 최댓값으로 갱신
			for (int j = 0; j < M; j++) {
				dp[i][j] = Math.max(leftOrder[j], rightOrder[j]);
			}
		}
		
		System.out.print(dp[N - 1][M - 1]);
	}
}	
