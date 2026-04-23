import java.io.*;
import java.util.*;

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
	
	static void dfs(int cnt) {
		if (cnt == 5) {
			maxBlock = Math.max(maxBlock, getMaxBlock());
			return;
		}
		
		int[][] backup = new int[N][N];
		for (int i = 0; i < N; i++) backup[i] = map[i].clone();
		
		for (int d = 0; d < 4; d++) {
			move2D(d);  // 이동
			dfs(cnt + 1);
			
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
		
		
		maxBlock = 0;
		dfs(0);
		System.out.print(maxBlock);
	}
}
