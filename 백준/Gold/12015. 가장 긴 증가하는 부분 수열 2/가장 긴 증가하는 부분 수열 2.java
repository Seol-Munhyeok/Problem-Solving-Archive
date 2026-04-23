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
	
	static int lowerBound(int[] lis, int size, int target) {
		int left = 0;
		int right = size;  // [left, right)
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (lis[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return left;
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
			int pos = lowerBound(lis, size, x);
			
			lis[pos] = x;
			
			if (pos == size) {
				size++;
			}
		}
		
		System.out.println(size);
	}
}
