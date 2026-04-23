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
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = nextInt();
		int[] deg = new int[N + 1];
		List<Integer>[] adj = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			int u = nextInt(); int v = nextInt();
			deg[u]++; deg[v]++;
			adj[u].add(v); adj[v].add(u);
		}
		
		long D = 0, G = 0;
		// G(ㅈ)
		for (int i = 1; i <= N; i++) {
		    long n = deg[i];
		    if (n >= 3) G += n * (n - 1) * (n - 2) / 6;
		}
		
		// D(ㄷ)
		for (int u = 1; u <= N; u++) {
			for (int v : adj[u]) {
				if (u < v) {
					D += (long)(deg[u] - 1) * (deg[v] - 1);
				}
			}
		}
		
		if (D > G * 3) System.out.println("D");
		else if (D < G * 3) System.out.println("G");
		else System.out.println("DUDUDUNGA");
	}
}
