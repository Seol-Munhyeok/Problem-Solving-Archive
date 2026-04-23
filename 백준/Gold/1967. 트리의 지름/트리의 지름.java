import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
	
	static int N, farNode;
	static long maxDist;
	static List<int[]>[] adj; // { v, w }
	
	static void dfs(int cur, int parent, long dist) {
		if (dist > maxDist) {
			farNode = cur;
			maxDist = dist;
		}
		for (int[] c : adj[cur]) {
			int next = c[0], w = c[1];
			if (next == parent) continue;
			
			dfs(next, cur, dist + w);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = nextInt();
		adj = new List[N + 1];
		for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
		
		for (int i = 0; i < N - 1; i++) {
			int u = nextInt(), v = nextInt(), w = nextInt();
			adj[u].add(new int[] {v, w});
			adj[v].add(new int[] {u, w});
		}
		
		farNode = -1;
		maxDist = -1;
		dfs(1, -1, 0);
		int end1 = farNode;
		
		farNode = -1;
		maxDist = -1;
		dfs(end1, -1, 0);
		
		System.out.println(maxDist);
	}
}
