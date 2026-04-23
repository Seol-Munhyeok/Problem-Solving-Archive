import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
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
		
		int N = nextInt();
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());  // 최대힙
		PriorityQueue<Integer> right = new PriorityQueue<>();  // 최소힙
		
		int mid = 1_000_000_000;
		
		for (int i = 0; i < N; i++) {
			int a = nextInt();
			
			if (left.size() == right.size()) {
				left.offer(a);
			} else {
				right.offer(a);
			}
			
			// 순서가 꼬였으면 루트 교환
			if (!right.isEmpty() && left.peek() > right.peek()) {
				int l = left.poll();
				int r = right.poll();
				left.offer(r);
				right.offer(l);
			}
			
			sb.append(left.peek()).append("\n");
		}
		
		System.out.println(sb);
	}
}
