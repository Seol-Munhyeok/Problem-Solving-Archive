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
	
	static class Edge {
	    int to, cost;
	    Edge(int to, int cost) { this.to = to; this.cost = cost; }
	}

	static class State {
	    int node, dist;
	    State(int node, int dist) { this.node = node; this.dist = dist; }
	}
	
	static final int INF = 2_000_000_000;
	static int n, m, t, s, g, h, ghCost;
	static List<Edge>[] adj;  // {to, cost}
	static int[] dstCands;
	static List<Integer> answer;
	
	static int[] dijkstra(int start) {
		int[] minDist = new int[n + 1];
		Arrays.fill(minDist, INF);
		minDist[start] = 0;
		PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
		pq.offer(new State(start, 0));  // {정점, 정점과의 거리}
		
		while (!pq.isEmpty()) {
			State cur = pq.poll();
			if (cur.dist != minDist[cur.node]) continue;  // 이미 갱신된 거리인지 확인
			
			for (Edge e : adj[cur.node]) {
				int nd = minDist[cur.node] + e.cost;
				if (nd < minDist[e.to]) {
					minDist[e.to] = nd;
					pq.offer(new State(e.to, nd));
				}
			}
		}
		
		return minDist;
	}
	
	static void solve() {
		int[] distS = dijkstra(s);
		int[] distG = dijkstra(g);
		int[] distH = dijkstra(h);
		
		for (int x : dstCands) {
			if (distS[x] == distS[g] + ghCost + distH[x] ||
			        distS[x] == distS[h] + ghCost + distG[x]) {
			        answer.add(x);
		    }
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = nextInt(); m = nextInt(); t = nextInt();
			s = nextInt(); g = nextInt(); h = nextInt();
			
			ghCost = -1;
			
			adj = new List[n + 1];
			for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
			dstCands = new int[t];
			
			for (int i = 0; i < m; i++) {
				int a = nextInt(), b = nextInt(), d = nextInt();
				adj[a].add(new Edge(b, d));
				adj[b].add(new Edge(a, d));
				
				if ((a == g && b == h) || (a == h && b == g)) {
					ghCost = d;
				}
			}
			for (int i = 0; i < t; i++) {
				int x = nextInt();
				dstCands[i] = x;
			}
			
			answer = new ArrayList<>();
			solve();
			Collections.sort(answer);
			
			for (int num : answer) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
