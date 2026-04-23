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
	
	static final long INF = Long.MAX_VALUE;
	static int N, M;
	static long[] arr;
	static long[] tree;
	
	static long init(int node, int start, int end) {
		if (start == end) return tree[node] = arr[start];
		int mid = (start + end) / 2;
		return tree[node] = Math.min(
				init(node * 2, start, mid),
				init(node * 2 + 1, mid + 1, end)
		);
	}
	
	static long queryMin(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		// 완전 불포함
		if (nodeRight < queryLeft || queryRight < nodeLeft) return INF;
		// 완전 포함
		if (queryLeft <= nodeLeft && nodeRight <= queryRight) return tree[node];
		// 부분 겹침: 더 쪼개서 왼쪽/오른쪽 계산
		int mid = (nodeLeft + nodeRight) / 2;
		return Math.min(
				queryMin(node * 2, nodeLeft, mid, queryLeft, queryRight),
				queryMin(node * 2 + 1, mid + 1, nodeRight, queryLeft, queryRight)
		);
	}
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = nextInt(); M = nextInt();
		arr = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = nextInt();
		}
		
		tree = new long[4 * N];
		init(1, 1, N);
		for (int i = 0; i < M; i++) {
			int a = nextInt();
			int b = nextInt();
			sb.append(queryMin(1, 1, N, a, b)).append("\n");
		}
		
		System.out.println(sb);
	}
}
