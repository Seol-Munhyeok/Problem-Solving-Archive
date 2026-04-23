import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};
	
	public static String next() throws Exception {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}
	
	public static int nextInt() throws Exception {
		return Integer.parseInt(next());
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = nextInt();
		int M = nextInt();
		int[][] arr = new int[N][N];
		int y = N / 2, x = N / 2;
		int answer_y = y + 1, answer_x = x + 1;
		int num = 1, step = 1, dir = 0;
		
		arr[y][x] = num++;
		while (num <= N*N) {
			for (int repeat = 0; repeat < 2; repeat++) {
				if (num > N*N) break;
				for (int i = 0; i < step; i++) {
					if (num > N*N) break;
					y += dy[dir];
					x += dx[dir];
					arr[y][x] = num;
					if (num == M) {
						answer_y = y + 1; answer_x = x + 1;  // 1-based
					}
					num++;
				}
				dir = (dir + 1) % 4;
			}
			step++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		sb.append(answer_y).append(" ").append(answer_x);
		
		System.out.print(sb);
		
	}
}
