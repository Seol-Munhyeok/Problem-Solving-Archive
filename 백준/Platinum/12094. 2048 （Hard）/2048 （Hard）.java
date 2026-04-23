import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	
	static int N, maxBlock, map[][];
	
	static void deepCopyMap(int[][] temp) {
		for (int i = 0; i < N; i++) {
			map[i] = temp[i].clone(); 
		} 
	}
	
	static void rotate90() {
		int[][] rotated = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rotated[j][N - i - 1] = map[i][j];
			}
		}
		deepCopyMap(rotated);
	}
	
	static int[] moveLeft1D(int[] arr) {
		int[] moved = new int[N];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] == 0) continue;
			if (moved[idx] == 0) moved[idx] = arr[i];
			else if (moved[idx] == arr[i]) moved[idx++] *= 2;
			else moved[++idx] = arr[i];
		}
		
		return moved;
	}
	
	static void moveLeft2D() {
		for (int i = 0; i < N; i++) {
			map[i] = moveLeft1D(map[i]);
		}
	}
	
	static void move2D(int dir) {
		// dir = 0 (왼쪽), dir = 1 (아래), dir = 2 (오른쪽), dir = 3 (위쪽)
		for (int i = 0; i < dir; i++) rotate90();
		moveLeft2D();
		for (int i = 0; i < (4 - dir) % 4; i++) rotate90();
	}
	
	static int getMaxBlock() {
		int answer = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, map[i][j]);
			}
		}
		return answer;
	}
	
	static boolean isSame(int[][] a, int[][] b) {
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            if (a[i][j] != b[i][j]) return false;
	        }
	    }
	    return true;
	}

	static void dfs(int cnt) {
		int curMaxBlock = getMaxBlock();
		if (cnt == 10) {
			maxBlock = Math.max(maxBlock, curMaxBlock);
			return;
		}
		
		// 가지치기 : 앞으로 남은 움직임으로 현재 최댓값을 넘기지 못하는 경우
		if (curMaxBlock * (1 << (10 - cnt)) <= maxBlock) return;
		
		for (int d = 0; d < 4; d++) {
			// 이동 전 상태 저장
			int[][] backup = new int[N][N];
			for (int i = 0; i < N; i++) backup[i] = map[i].clone();
			
			move2D(d);  // 이동
			
			// 가지치기 : 이동 후 변화가 있을 때만 탐색
			if (!isSame(backup, map)) {
			    dfs(cnt + 1);
			}
			
			for (int i = 0; i < N; i++) map[i] = backup[i].clone();  // 원상 복구
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = nextInt();
			}
		}
		
		
		maxBlock = getMaxBlock();  // 10번을 움직이는 것이 불가능하면 답이 갱신되지 않으므로 미리 저장
		dfs(0);
		System.out.print(maxBlock);
	}
}
