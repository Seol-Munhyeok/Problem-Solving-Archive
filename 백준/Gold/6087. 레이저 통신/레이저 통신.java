import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
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
	static final int INF = 1_000_000_000;
	static int W, H;
	static List<int[]> lasers;
	static char[][] map;
	static int[][][] minDist;  // dist[y][x][dir] = 꺾은 횟수, INF (미방문)
	
	static class State {
		int y, x, dir, cost;
		State(int y, int x, int dir, int cost) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cost = cost;
		}
	}
	
	static int dijkstra(int sy, int sx, int ey, int ex) {
		minDist = new int[H][W][4];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				Arrays.fill(minDist[i][j], INF);
			}
		}
		
		PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
		for (int d = 0; d < 4; d++) {
			pq.offer(new State(sy, sx, d, 0));
			minDist[sy][sx][d] = 0;
		}
		
		while (!pq.isEmpty()) {
			State cur = pq.poll();
			
			if (cur.cost != minDist[cur.y][cur.x][cur.dir]) continue;
			if (cur.y == ey && cur.x == ex) return cur.cost;
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d], nx = cur.x + dx[d];
				if (ny >= H || ny < 0 || nx >= W || nx < 0) continue;
				if (map[ny][nx] == '*') continue;
				
				int nd = cur.cost + (cur.dir != d ? 1 : 0);
				
				if (nd < minDist[ny][nx][d]) {
					minDist[ny][nx][d] = nd;
					pq.offer(new State(ny, nx, d, nd));
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		W = nextInt(); H = nextInt();
		map = new char[H][W];
		lasers = new ArrayList<>();
		
		// map 입력 처리
		for (int i = 0; i < H; i++) {
			String line = next();
			for (int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'C') {
					lasers.add(new int[] {i, j});
				}
			}
		}
		
		int sy = lasers.get(0)[0];
		int sx = lasers.get(0)[1];
		int ey = lasers.get(1)[0];
		int ex = lasers.get(1)[1];
		
		int answer = dijkstra(sy, sx, ey, ex);
		
		System.out.println(answer);
	}
}
