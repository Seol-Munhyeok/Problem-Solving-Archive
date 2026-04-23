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
	
	static int lowerBound(int[] lis, int size, int target) {
		int left = 0, right = size;
		
		while (left < right) {
			int mid = (left + right) / 2;
			if (lis[mid] >= target) right = mid;
			else left = mid + 1;
		}
		return left;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = nextInt();
		}
		
		int[] lisValue = new int[N];  // 길이 pos+1인 증가 부분 수열의 최소 끝값
		int[] lisIndex = new int[N];  // 그 끝값이 원본 배열에서 몇 번째 인덱스인지
		int[] prev = new int[N];
		
		int size = 0;
		
		for (int i = 0; i < N; i++) {
			int pos = lowerBound(lisValue, size, arr[i]);
			
			// 이전 원소 연결
			prev[i] = (pos == 0) ? -1 : lisIndex[pos - 1];
			
			// 상태 갱신
			lisValue[pos] = arr[i];
			lisIndex[pos] = i;
			
			// 길이 증가
			if (pos == size) size++;
		}
		
		// 복원
		int[] result = new int[size];
		int cur = lisIndex[size - 1];
		
		for (int i = size - 1; i >= 0; i--) {
			result[i] = arr[cur];
			cur = prev[cur];
		}
		
		// 출력
		sb.append(size).append("\n");
		for (int x : result) {
			sb.append(x).append(" ");
        }
		System.out.println(sb);
	}
}
