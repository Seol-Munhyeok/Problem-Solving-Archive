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
	
	static final long INF = Long.MAX_VALUE / 2;
	static final int MOD = 1_000_000_007;
	static int[] arr;
	static long[] tree;
	
	static long init(int node, int start, int end) {
		if (start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return tree[node] = 
				(init(node * 2, start, mid) % MOD
				* init(node * 2 + 1, mid + 1, end) % MOD) % MOD;
	}
	
	// [start, end] : 현재 노드가 담당하는 구간
	// [left, right] : 쿼리 구간
	static long query(int node, int start, int end, int left, int right) {
		// 완전 불포함
		if (end < left || right < start) return 1;
		
		// 완전 포함
		if (left <= start && end <= right) return tree[node];
		
		// 구간에 걸쳐 있음
		int mid = (start + end) / 2;
		return (query(node * 2, start, mid, left, right) % MOD
				* query(node * 2 + 1, mid + 1, end, left, right) % MOD) % MOD;
	}
	
	// node: 현재 세그트리 노드 번호
	// [start, end]: node가 담당하는 구간
	// idx: 값을 변경할 배열의 위치
	// newValue: 해당 위치에 넣을 새로운 값
	static void update(int node, int start, int end, int idx, int newValue) {
		if (start == end) {
			tree[node] = newValue;
			return;
		}
		
		int mid = (start + end) / 2;
		if (idx <= mid) {
			update(node * 2, start, mid, idx, newValue);
		} else {
			update(node * 2 + 1, mid + 1, end, idx, newValue);
		}
		
		tree[node] = ((tree[node * 2] % MOD) * (tree[node * 2 + 1] % MOD)) % MOD;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = nextInt();
		int M = nextInt();
		int K = nextInt();
		arr = new int[N + 1];
		tree = new long[4 * N];
		for (int i = 1; i <= N; i++) {
			arr[i] = nextInt();
		}
		
		init(1, 1, N);
		
		for (int i = 0; i < M + K; i++) {
			int a = nextInt();
			int b = nextInt();
			int c = nextInt();
			
			if (a == 1) update(1, 1, N, b, c);
			else sb.append(query(1, 1, N, b, c)).append("\n");
		}
		
		System.out.println(sb);
	}
}
