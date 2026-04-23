import java.io.*;
import java.util.*;

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
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = nextInt();
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(nextInt());
		}
		
		int M = nextInt();
		for (int i = 0; i < M; i++) {
			int answer = set.contains(nextInt()) ? 1 : 0;
			sb.append(answer).append(" ");
		}
		System.out.print(sb);
	}
}
