import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
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
	
	static final int[] dy = {-1, 0, 1, 0}; 
	static final int[] dx = {0, 1, 0, -1};
	static int N, M, K;
	static int[][] map;
	// [y][x][broken][day] = (y, x)에 broken 횟수로 벽을 부술 때 최단 거리. day=0(낮), day=1(밤)
	static int[][][][] visited;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = nextInt(); M = nextInt(); K = nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = next();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		visited = new int[N][M][K + 1][2];  // 0 = 미방문
		
		Queue<int[]> q = new ArrayDeque<>();  // {y, x, broken, day}
		q.offer(new int[] {0, 0, 0, 0});
		visited[0][0][0][0] = 1;  // 시작 지점도 포함
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0], x = cur[1], brokenCnt = cur[2], day = cur[3];
			int curDist = visited[y][x][brokenCnt][day];
			
			if (y == N - 1 && x == M - 1) {
				System.out.println(curDist);
				return;
			}
			
			// 밤일 때, 낮 기다리기
			if (day == 1 && visited[y][x][brokenCnt][day ^ 1] == 0) {
				q.offer(new int[] {y, x, brokenCnt, day ^ 1});
				visited[y][x][brokenCnt][day ^ 1] = curDist + 1;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d]; int nx = x + dx[d];
				
				if (ny >= N || ny < 0 || nx >= M || nx < 0) continue;
				
				// 벽 안 부수고 이동
				if (map[ny][nx] == 0 && visited[ny][nx][brokenCnt][day ^ 1] == 0) {
					q.offer(new int[] {ny, nx, brokenCnt, day ^ 1});
					visited[ny][nx][brokenCnt][day ^ 1] = curDist + 1;
				}
				
				// 낮일 때, 벽 부수고 이동
				if (day == 0 && brokenCnt < K && map[ny][nx] == 1 
						&& visited[ny][nx][brokenCnt + 1][day ^ 1] == 0) {
					q.offer(new int[] {ny, nx, brokenCnt + 1, day ^ 1});
					visited[ny][nx][brokenCnt + 1][day ^ 1] = curDist + 1;
				}
			}
		}
		
		System.out.println(-1);
	}
}