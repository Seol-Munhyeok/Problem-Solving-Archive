import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
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
	
	static int N;
	static List<Integer>[] graph;
	
 	public static void main(String[] args) throws Exception {
 		br = new BufferedReader(new InputStreamReader(System.in));
 		sb = new StringBuilder();
 		
 		N = nextInt();
 		graph = new List[N + 1];
 		for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
 		
 		for (int i = 0; i < N; i++) {
 			int a = nextInt();
 			int b = nextInt();
 			graph[a].add(b);
 			graph[b].add(a);
 		}
 		
 		// 각 정점의 차수 계산
 		int[] degree = new int[N + 1];
 		for (int i = 1; i <= N; i++) {
 			degree[i] = graph[i].size();
 		}
 		
 		// 사이클 판별
 		Queue<Integer> q = new ArrayDeque<>();
 		boolean[] removed = new boolean[N + 1];
 		
 		for (int i = 1; i <= N; i++) {
 			if (degree[i] == 1) q.offer(i);
 		}
 		
 		while (!q.isEmpty()) {
 			int cur = q.poll();
 			removed[cur] = true;
 			
 			for (int next : graph[cur]) {
 				if (removed[next]) continue;
 				degree[next]--;
 				if (degree[next] == 1) q.offer(next);
 			}
 		}
 		
 		// 모든 사이클 노드로부터 BFS 수행
 		int[] dist = new int[N + 1];
 		Arrays.fill(dist, -1);
 		
 		Queue<Integer> bfsQ = new ArrayDeque<>();
 		
 		for (int i = 1; i <= N; i++) {
 			if (!removed[i]) {
 				dist[i] = 0;
 				bfsQ.offer(i);
 			}
 		}
 		
 		while (!bfsQ.isEmpty()) {
 			int cur = bfsQ.poll();
 			
 			for (int next : graph[cur]) {
 				if (dist[next] != -1) continue;
 				
 				dist[next] = dist[cur] + 1;
 				bfsQ.offer(next);
 			}
 		}
 		
 		// 결과 출력
 		for (int i = 1; i <= N; i++) {
 			sb.append(dist[i]).append(" ");
 		}
 		System.out.println(sb);
	}
}
