import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};
	
	static int[][] arr;
	static boolean[][] visited;
	
	static String next() throws Exception {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}
	
	static int nextInt() throws Exception {
		return Integer.parseInt(next());
	}
	
	static int bfs(int y, int x) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {y, x});
		visited[y][x] = true;
		int count = 1;
	
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0]; int cx = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i]; int nx = cx + dx[i];
				if (ny >= N || ny < 0 || nx >= N || nx < 0) continue;
				if (visited[ny][nx] || arr[ny][nx] == 0) continue;
				q.offer(new int[] {ny, nx});
				count++;
				visited[ny][nx] = true;
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = nextInt();
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = next();
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		
		int danJiCount = 0;
		List<Integer> jipCount = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					danJiCount++;
					jipCount.add(bfs(i, j));
				}
			}
		}
		Collections.sort(jipCount);
		
		sb.append(danJiCount).append("\n");
		for (int jip : jipCount) {
			sb.append(jip).append("\n");
		}
		System.out.println(sb);
	}
}
