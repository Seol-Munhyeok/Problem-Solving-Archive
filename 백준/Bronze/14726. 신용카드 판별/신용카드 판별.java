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
		
		int T = nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int[] nums = new int[16];
			String line = next();
			for (int i = 0; i < 16; i++) {
				nums[i] = line.charAt(i) - '0';
			}
			
			for (int i = 14; i >= 0; i -= 2) {
				int temp = nums[i] * 2;
				if (temp >= 10) {
					temp = (temp % 10) + (temp / 10);
				}
				nums[i] = temp;
			}
			
			int temp = 0;
			for (int i = 0; i < 16; i++) {
				temp += nums[i];
			}
			
			if (temp % 10 == 0) sb.append("T").append("\n");
			else sb.append("F").append("\n");
		}
		
		System.out.println(sb);
	}
}
