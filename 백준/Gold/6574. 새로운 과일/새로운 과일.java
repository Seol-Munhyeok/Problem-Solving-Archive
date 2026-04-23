import java.io.*;
import java.util.*;

public class Main {

	static String solve(String a, String b) {
		int N = a.length();
		int M = b.length();
		int[][] lcs = new int[N + 1][M + 1];
		
		// lcs dp 배열 채우기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}
		
		// lcs 역추적 (lcs 문자열에 해당하는 인덱스를 저장)
		int i = N;  // 문자열 a
		int j = M;  // 문자열 b
		
		StringBuilder sb = new StringBuilder();
		while (i > 0 && j > 0) {
			if (a.charAt(i - 1) == b.charAt(j - 1)) {
				sb.append(a.charAt(i - 1));
				i--; j--;
			} else if (lcs[i - 1][j] > lcs[i][j - 1]) {
				sb.append(a.charAt(i - 1));
				i--;
			} else {
				sb.append(b.charAt(j - 1));
				j--;
			}
		}
		
		while (i > 0) sb.append(a.charAt(--i));
		while (j > 0) sb.append(b.charAt(--j));
		
		return sb.reverse().toString();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			
			String a = st.nextToken();
			String b = st.nextToken();
			sb.append(solve(a, b)).append("\n");
		}
		
		System.out.println(sb);
	}
}
