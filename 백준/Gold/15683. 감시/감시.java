import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	static class CCTV {
		int id, y, x;
		CCTV (int id, int y, int x) { this.id = id; this.y = y; this.x = x; }
	}
	
	static int N, M, cctvCnt, minAnswer;
	static List<CCTV> cctvs, cctvFives;
	static int[][] arr;
	static int[] cctvDirs;
	
	static void monitorLeft(int id, int y, int x) {
		for (int i = 0; x - i >= 0 && i < M; i++) {
			if (arr[y][x - i] == 6) return;
			arr[y][x - i] = id;
		}
	}
	
	static void monitorRight(int id, int y, int x) {
		for (int i = 0; x + i < M && i < M; i++) {
			if (arr[y][x + i] == 6) return;
			arr[y][x + i] = id;
		}
	}
	
	static void monitorUp(int id, int y, int x) {
		for (int i = 0; y - i >= 0 && i < N; i++) {
			if (arr[y - i][x] == 6) return;
			arr[y - i][x] = id;
		}
	}
	
	static void monitorDown(int id, int y, int x) {
		for (int i = 0; y + i < N && i < N; i++) {
			if (arr[y + i][x] == 6) return;
			arr[y + i][x] = id;
		}
	}
	
	static void monitorOne(int y, int x, int dir) {
		if (dir == 0) monitorRight(1, y, x);
		else if (dir == 1) monitorDown(1, y, x);
		else if (dir == 2) monitorLeft(1, y, x);
		else monitorUp(1, y, x);
	}
	
	static void monitorTwo(int y, int x, int dir) {
		if (dir == 0 || dir == 2) {
			monitorLeft(2, y, x);
			monitorRight(2, y, x);
		}
		else {
			monitorUp(2, y, x);
			monitorDown(2, y, x);
		}
	}
	
	static void monitorThree(int y, int x, int dir) {
		if (dir == 0) {
			monitorUp(3, y, x);
			monitorRight(3, y, x);
		}
		else if (dir == 1) {
			monitorRight(3, y, x);
			monitorDown(3, y, x);
		}
		else if (dir == 2) {
			monitorLeft(3, y, x);
			monitorDown(3, y, x);
		}
		else {
			monitorLeft(3, y, x);
			monitorUp(3, y, x);
		}
	}
	
	static void monitorFour(int y, int x, int dir) {
		if (dir == 0) {
			monitorUp(4, y, x);
			monitorLeft(4, y, x);
			monitorRight(4, y, x);
		}
		else if (dir == 1) {
			monitorUp(4, y, x);
			monitorRight(4, y, x);
			monitorDown(4, y, x);
		}
		else if (dir == 2) {
			monitorRight(4, y, x);
			monitorDown(4, y, x);
			monitorLeft(4, y, x);
		}
		else {
			monitorUp(4, y, x);
			monitorLeft(4, y, x);
			monitorDown(4, y, x);
		}
	}
	
	static void monitorFive(int y, int x) {
		monitorUp(5, y, x);
		monitorRight(5, y, x);
		monitorLeft(5, y, x);
		monitorDown(5, y, x);
	}
	
	static void monitor(CCTV cctv, int dir) {
		int id = cctv.id;
		int y = cctv.y;
		int x = cctv.x;
		
		if (id == 1) monitorOne(y, x, dir);
		else if (id == 2) monitorTwo(y, x, dir);
		else if (id == 3) monitorThree(y, x, dir);
		else if (id == 4) monitorFour(y, x, dir);
	}
	
	static int countNotMonitoringZone() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}
	
	static void startMonitoring() {
		for (int i = 0; i < cctvCnt; i++) {
			monitor(cctvs.get(i), cctvDirs[i]);
		}
	}
	
	static void dfs(int idx) {
		if (idx == cctvCnt) {
			startMonitoring();
			minAnswer = Math.min(minAnswer, countNotMonitoringZone());
			return;
		}
		
		for (int dir = 0; dir < 4; dir++) {
			cctvDirs[idx] = dir;
			// 원본 배열 복사
			int[][] backup = new int[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(arr[i], 0, backup[i], 0, M);
			}
			
			dfs(idx + 1);
			
			// 원상 복구
			for (int i = 0; i < N; i++) {
				System.arraycopy(backup[i], 0, arr[i], 0, M);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = nextInt(); M = nextInt();
		arr = new int[N][M];
		cctvs = new ArrayList<>();
		cctvFives = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int val = nextInt();
				arr[i][j] = val;
				if (val != 0 && val != 5 && val != 6) {
					cctvs.add(new CCTV(val, i, j));
				}
				else if (val == 5) {
					cctvFives.add(new CCTV(val, i, j));
				}
			}
		}
		
		// 5번 CCTV는 미리 감시 (회전해도 변하지 않음)
		for (CCTV c : cctvFives) {
			monitorFive(c.y, c.x);
		}
		
		cctvCnt = cctvs.size();
		cctvDirs = new int[cctvCnt];
		
		minAnswer = 1_000_000_000;
		dfs(0);
		System.out.println(minAnswer);
	}
}
