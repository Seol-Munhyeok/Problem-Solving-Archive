import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = nextInt();
		}
		
		int[] lis = new int[N];
		int size = 0;
		
		for (int x : arr) {
			int pos = Arrays.binarySearch(lis, 0, size, x);
			
			if (pos < 0) {
				pos = -(pos + 1);
			}
			
			lis[pos] = x;
			
			if (pos == size) {
				size++;
			}
		}
		
		System.out.println(size);
	}
}
