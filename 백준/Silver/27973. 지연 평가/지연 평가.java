import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
		
		int Q = nextInt();
		long minValue = 1;
		long gap = 1;
		
		int x = 0;
		for (int i = 0; i < Q; i++) {
			int cmd = nextInt();
			if (cmd == 3) sb.append(minValue).append("\n");
			else if (cmd == 0) {
				x = nextInt();
				minValue += x;
			} else if (cmd == 1) {
				x = nextInt();
				minValue *= x;
				gap *= x;
			} else if (cmd == 2) {
				x = nextInt();
				minValue += (x * gap);
			}
		}
		
		System.out.println(sb);
	}
}
