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
	
	static int N;
	
	static boolean isSame(char[] src, char[] dst) {
		for (int i = 0; i < N; i++) {
			if (src[i] != dst[i]) return false;
		}
		return true;
	}
	
	static int solve(char[] src, char[] dst) {
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			// i - 1번 째 수를 바꿀 기회는 이때가 유일함
			if (src[i - 1] != dst[i - 1]) {
				cnt++;
				// 인접한 수 모두 토글
				src[i - 1] = (src[i - 1] == '0' ? '1' : '0');
				src[i] = (src[i] == '0' ? '1' : '0');
				if (i < N - 1) src[i + 1] = (src[i + 1] == '0' ? '1' : '0');
			}
		}
		
		return isSame(src, dst) ? cnt : -1;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = nextInt();
		char[] src = new char[N];
		char[] dst = new char[N];
		
		src = next().toCharArray();
		dst = next().toCharArray();
		
		char[] backup = src.clone();
		
		// 1번 스위치를 안 누르는 경우
		int answer1 = solve(src, dst);
		
		// 0번 스위치를 누르고 다시 확인
		backup[0] = (backup[0] == '0' ? '1' : '0');
		backup[1] = (backup[1] == '0' ? '1' : '0');
		
		int answer2 = solve(backup, dst);  
		if (answer2 != -1) answer2++;  // 처음에 한 번 누른 거 반영
		
		if (answer1 == -1 && answer2 == -1) System.out.println(-1);
		else if (answer1 == -1) System.out.println(answer2);
		else if (answer2 == -1) System.out.println(answer1);
		else System.out.println(Math.min(answer1, answer2));
	}
}
