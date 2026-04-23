import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	
	static int n;
	static Deque<Integer> dq;
	
	static void riffle() {
		Deque<Integer> a = new ArrayDeque<>();
		Deque<Integer> b = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			a.offerLast(dq.pollFirst());
		}
		
		for (int i = 0; i < n; i++) {
			b.offerLast(dq.pollFirst());
		}
		
		for (int i = 0; i < n; i++) {
			dq.offerLast(a.pollFirst());
			dq.offerLast(b.pollFirst());
		}
	}
	
	static void cut(int k) {
		for (int i = 0; i < k; i++) {
			int num = dq.pollFirst();
			dq.offerLast(num);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		int m = sc.nextInt();
		dq = new ArrayDeque<>();
		
		for (int i = 1; i <= 2 * n; i++) {
			dq.offerLast(i);
		}
		
		for (int i = 0; i < m; i++) {
			int k = sc.nextInt();
			if (k == 0) riffle();
			else cut(k);
		}
		
		StringBuilder sb = new StringBuilder();
		while (!dq.isEmpty()) {
			sb.append(dq.pollFirst()).append("\n");
		}
		
		System.out.println(sb);
	}
}
