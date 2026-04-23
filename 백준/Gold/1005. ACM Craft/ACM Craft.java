import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
	
	static int N, K, W;
	static int[] buildTime, dp;
	static List<Integer>[] adj;
	
	static int go(int x) {
		if (dp[x] != -1) return dp[x];
		
		dp[x] = buildTime[x];  // 선행 건물이 하나도 없으면 자기 시간만 필요
		for (int prev : adj[x]) {
			dp[x] = Math.max(dp[x], go(prev) + buildTime[x]);
		}
		
		return dp[x];
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = nextInt();  // 건물의 개수
			K = nextInt();  // 건설순서 규칙 개수
			buildTime = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				buildTime[i] = nextInt();
			}
			
			adj = new List[N + 1];
			for (int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < K; i++) {
				int x = nextInt();
				int y = nextInt();
				adj[y].add(x);  // Top-Down으로 풀이하므로 역방향으로 저장
			}
			
			W = nextInt();  // 건설해야 할 건물 번호
			
			dp = new int[N + 1];  // dp[i] = i 건물을 세우는데 필요한 최소 시간
			Arrays.fill(dp, -1);
			
			sb.append(go(W)).append("\n");
		}
		
		System.out.println(sb);
	}
}
