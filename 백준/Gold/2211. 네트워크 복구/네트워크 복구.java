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
	
	static final int INF = 1_000_000_000;
	static int N, M;
	static int[] parent;
	static List<Edge>[] adj;
	static HashSet<List<Integer>> edges;
	
	static void dijkstra(int start) {
		int[] minDist = new int[N + 1];
		Arrays.fill(minDist, INF);
		minDist[start] = 0;
		Arrays.fill(parent, -1);

		PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
		pq.offer(new State(start, 0));
		
		while (!pq.isEmpty()) {
			State cur = pq.poll();
			if (cur.dist != minDist[cur.node]) continue;  // 오래된 상태 제외
			
			for (Edge edge : adj[cur.node]) {
				int nd = minDist[cur.node] + edge.cost;
				if (minDist[edge.to] > nd) {
					minDist[edge.to] = nd;
					parent[edge.to] = cur.node;
					pq.offer(new State(edge.to, nd));
				}
			}
		}
	}
	
	static void printPath(int start) {
		List<Integer> path = new ArrayList<>();
		int end = start;
		while (end != -1) {
			path.add(end);
			end = parent[end];
		}
		
		for (int i = 0; i < path.size() - 1; i++) {
			if (edges.contains(Arrays.asList(path.get(i + 1), path.get(i)))) continue;
			edges.add(Arrays.asList(path.get(i), path.get(i + 1)));
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = nextInt(); M = nextInt();
		adj = new List[N + 1];
		for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			int a = nextInt(), b = nextInt(), c = nextInt();
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
		}
		
		parent = new int[N + 1];
		dijkstra(1);
		edges = new HashSet<>();
		
		for (int i = 2; i <= N; i++) {
			printPath(i);
		}
		
		sb.append(N - 1).append("\n");  // 복구할 회선의 개수 = N - 1
		for (List<Integer> v : edges) {
			sb.append(v.get(0)).append(" ").append(v.get(1)).append("\n");
		}
		System.out.println(sb);
	}
}	
