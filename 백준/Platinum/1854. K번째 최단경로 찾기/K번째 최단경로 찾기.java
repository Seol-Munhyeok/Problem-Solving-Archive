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
	static int[] count;  // 해당 정점 몇 번 방문했는지
	static long[] answer; // 각 정점의 K번째 최단경로 저장
	
	static void dijkstra(int start) {
		PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
		count = new int[n + 1];
		
		pq.offer(new State(start, 0));
		
		while (!pq.isEmpty()) {
			State cur = pq.poll();
			
			count[cur.node]++;
			
			// k번째로 도착한 순간이 답
			if (count[cur.node] == k) {
				answer[cur.node] = cur.dist;
			}
			
			// k번 넘게 방문하면 더 볼 필요 없음
		    if (count[cur.node] > k) continue;
		    
			for (Edge edge : adj[cur.node]) {
				int next = edge.to;
				long nd = cur.dist + edge.cost;
				
				pq.add(new State(edge.to, nd));
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
		
		answer = new long[n + 1];
		
		Arrays.fill(answer, -1);
		dijkstra(1);
		
		for (int i = 1; i <= n; i++) {
			sb.append(answer[i]).append("\n");
		}
		
		System.out.println(sb);
	}
}
