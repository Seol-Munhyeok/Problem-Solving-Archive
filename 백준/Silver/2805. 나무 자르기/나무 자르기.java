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
	
	static long cut(int[] arr, int h) {
		long total = 0;
		for (int a : arr) {
			total += Math.max(a - h, 0);
		}
		return total;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = nextInt(); int M = nextInt();
		int[] arr = new int[N];
		int max = -1;
		for (int i = 0; i < N; i++) {
			arr[i] = nextInt();
			max = Math.max(max, arr[i]);
		}
		
		int start = 0, end = max;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (cut(arr, mid) >= M) start = mid + 1;
			else end = mid - 1;
		}
		System.out.println(start - 1);
	}
}
