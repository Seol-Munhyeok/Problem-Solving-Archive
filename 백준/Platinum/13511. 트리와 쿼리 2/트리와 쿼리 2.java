import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	static final int LOG = 21; // 2^20 = 1,000,000
	static int N;  			   // 정점의 수
	static List<int[]>[] adj;  // {v, w}
	static int[] depth;  	   // 루트로부터 깊이
	static long[] dist;  	   // 루트로부터 누적 거리
	static boolean[] visited;  // 각 노드의 깊이가 계산되었는지 여부
	static int[][] parent;     // parent[k][v] = v의 2^k번째 조상
	
	// 루트 노드부터 시작하여 깊이를 구하는 함수
	static void dfs(int x, int dep) {
		visited[x] = true;
		depth[x] = dep;
		
		for (int[] next : adj[x]) {
			int y = next[0];
			int w = next[1];
			
			if (visited[y]) continue;  // 이미 깊이를 구했다면 넘기기
			
			parent[0][y] = x;   	   // 바로 위의 조상 저장 (2^0 = 1)
			dist[y] = dist[x] + w;
			
			dfs(y, dep + 1);
		}
	}
	
	// 전체 부모 관계를 설정하는 함수
	static void setParent() {
		dfs(1, 0);  // 루트 노드는 1번 노드
		for (int k = 1; k < LOG; k++) {
			for (int v = 1; v <= N; v++) {
				int midNode = parent[k - 1][v];
	            parent[k][v] = (midNode == 0) ? 0 : parent[k - 1][midNode];
			}
		}
	}
	
	// v를 step만큼 위로 올리기
	static int jump(int v, int step) {
		for (int i = 0; i < LOG; i++) {
			if ((step & (1 << i)) != 0) v = parent[i][v];
		}
		return v;
	}
	
	// 노드 a, b의 LCA를 찾는 함수
	static int lca(int a, int b) {
		// b가 더 깊도록 설정
		if (depth[a] > depth[b]) { int t = a; a = b; b = t; }

		// 먼저 깊이가 동일하도록
		b = jump(b, depth[b] - depth[a]);
		
		// 부모가 같아지도록
		if (a == b) return a;
		
		for (int i = LOG - 1; i >= 0; i--) {
			// 조상을 향해 거슬러 올라가기
			if (parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		// 이후의 부모가 찾고자 하는 조상
		return parent[0][a];
	}
	
	static long getDist(int a, int b) {
		int c = lca(a, b);
		return dist[a] + dist[b] - 2L * dist[c];
	}
	
	static int kthOnPath(int u, int v, int k) {
		int c = lca(u, v);
		int du = depth[u] - depth[c];
		int dv = depth[v] - depth[c];
		int total = du + dv + 1;
		
		// u에서 lca 방향
		if (k <= du + 1) return jump(u, k - 1);
		// lca에서 v 방향 => v에서 거꾸로 올림
		return jump(v, total - k);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = nextInt();
		adj = new List[N + 1];
		for (int i = 0; i <= N; i++) adj[i] = new ArrayList<>();
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		dist = new long[N + 1];
		parent = new int[LOG][N + 1];
		
		for (int r = 0; r < N - 1; r++) {
			int u = nextInt(); int v = nextInt(); int w = nextInt();
			adj[u].add(new int[] {v, w});
			adj[v].add(new int[] {u, w});
		}
		
		setParent();
		
		sb = new StringBuilder();
		int M = nextInt();
		for (int r = 0; r < M; r++) {
			int a = nextInt();
			if (a == 1) {
				int u = nextInt(); int v = nextInt();
				sb.append(getDist(u, v)).append("\n");
			}
			else {
				int u = nextInt(); int v = nextInt(); int k = nextInt();
				sb.append(kthOnPath(u, v, k)).append("\n");
			}
		}
		System.out.println(sb);
	}
}