import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
	
	static final int INF = 1_000_000_000;
	static final int MAX = 100_001;
	static int N;
	static int[] arr;
	static int[][][] dp;  // dp[l][r][idx] = (l,r)에서 idx부터 끝까지의 최소 비용
	
	static int cost(int from, int to) {
		if (from == to) return 1;
		if (from == 0) return 2;
		if (Math.abs(from - to) == 2) return 4;
		return 3;
	}
	
	static int dfs(int l, int r, int idx) {
		if (idx == N + 1) return 0;
		
		int memo = dp[l][r][idx];
		if (memo != -1) return memo;
		
		int target = arr[idx];
		int minPower = INF;
		
		// 왼발을 target으로 (단, 오른발이 이미 target이면 두 발 겹침 -> 불가)
		if (r != target) {
			minPower = Math.min(minPower, cost(l, target) + dfs(target, r, idx + 1));
		}
		
		// 오른발을 target으로 (단, 왼발이 이미 target이면 두 발 겹침 -> 불가)
		if (l != target) {
			minPower = Math.min(minPower, cost(r, target) + dfs(l, target, idx + 1));
		}
		
		dp[l][r][idx] = minPower;
		
		return minPower;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[MAX];  // 1-based
		N = 0;
		
		while (true) {
			int num = nextInt();
			if (num == 0) break;
			arr[++N] = num;
		}
		
		dp = new int[5][5][N + 2];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.println(dfs(0, 0, 1));
	}
}
