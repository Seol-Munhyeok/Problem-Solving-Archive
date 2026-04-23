import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int N = nextInt();
		int K = nextInt();
		
		int prev = 0, cur = 0;
		int answer = 0;
		for (int r = 0; r < N; r++) {
			cur = nextInt();
			if (prev >= cur) {
				cur += K;
				answer++;
			}
			if (prev >= cur) {
				System.out.println(-1);
				return;
			}
			
			prev = cur;
		}
		System.out.println(answer);
	}
}
