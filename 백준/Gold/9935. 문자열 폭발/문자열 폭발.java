import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
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


	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = next();
		String target = next();
		int targetLen = target.length();
		
		Deque<Character> stack = new ArrayDeque<>();
		for (char ch : str.toCharArray()) {
			stack.addLast(ch);
			if (stack.size() >= targetLen && ch == target.charAt(targetLen - 1)) {
				sb = new StringBuilder();
				for (int repeat = 0; repeat < targetLen; repeat++) {
					sb.append(stack.pollLast());
				}
				sb.reverse();
				if (!sb.toString().equals(target)) {
					for (int i = 0; i < sb.length(); i++) {
						stack.addLast(sb.charAt(i));
					}
				}
			}
		}
		
		sb = new StringBuilder();
		if (stack.isEmpty()) System.out.println("FRULA");
		else {
			while (!stack.isEmpty()) {
				sb.append(stack.pollLast());
			}
			sb.reverse();
			System.out.println(sb);
		}
	}
}