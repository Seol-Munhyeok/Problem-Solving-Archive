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
		int to;
		long cost;
		Edge(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static class State {
		int node;
		long dist;
		State(int node, long dist) {
			this.node = node;
			this.dist = dist;
		}
	}
	
	static final long INF = Long.MAX_VALUE / 4;
	static int n, m, k;
	static List<Edge>[] adj;
	static PriorityQueue<Long>[] best;
	
	static void dijkstra(int start) {
		PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
		
		best = new PriorityQueue[n + 1];
		for (int i = 1; i <= n; i++) {
			best[i] = new PriorityQueue<>(Collections.reverseOrder());   // 정점별 최대 힙
		}
		
		best[start].offer(0L);
		pq.offer(new State(start, 0));
		
		while (!pq.isEmpty()) {
			State cur = pq.poll();
			
			// stale check
			if (best[cur.node].size() == k && cur.dist > best[cur.node].peek()) continue;
			
			for (Edge edge : adj[cur.node]) {
				int next = edge.to;
				long nd = cur.dist + edge.cost;
				
				// 상위 k개 까지 best[]에 내림차순 순서대로 저장
				if (best[next].size() < k) {
					best[next].offer(nd);
					pq.offer(new State(next, nd));
				} else if (best[next].peek() > nd) {
					best[next].poll();
					best[next].offer(nd);
					pq.offer(new State(next, nd));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = nextInt(); m = nextInt(); k = nextInt();
		adj = new List[n + 1];
		for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			int a = nextInt();
			int b = nextInt();
			int c = nextInt();
			adj[a].add(new Edge(b, c));
		}
		
		dijkstra(1);
		
		for (int i = 1; i <= n; i++) {
			if (best[i].size() < k) sb.append(-1).append("\n");
			else sb.append(best[i].peek()).append("\n");
		}
		
		System.out.println(sb);
	}
}
