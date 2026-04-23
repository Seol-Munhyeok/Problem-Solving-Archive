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
	
	static long nextLong() throws Exception {
		return Long.parseLong(next());
	}
	
	static final int INF = 2_000_000_000;
	static int[] arr;
	static long[] maxTree, minTree;
	
	static long initMaxTree(int node, int start, int end) {
		if (start == end) return maxTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		long leftResult = initMaxTree(node * 2, start, mid);
		long rightResult = initMaxTree(node * 2 + 1, mid + 1, end);
		return maxTree[node] = Math.max(leftResult, rightResult);
	}
	
	static long initMinTree(int node, int start, int end) {
		if (start == end) return minTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		long leftResult = initMinTree(node * 2, start, mid);
		long rightResult = initMinTree(node * 2 + 1, mid + 1, end);
		
		return minTree[node] = Math.min(leftResult, rightResult);
	}
	
	static long queryMax(int node, int start, int end, int left, int right) {
		if (right < start || end < left) return -INF;
		
		if (left <= start && end <= right) return maxTree[node];
		
		int mid = (start + end) / 2;
		long leftResult = queryMax(node * 2, start, mid, left, right);
		long rightResult = queryMax(node * 2 + 1, mid + 1, end, left, right);
		
		return Math.max(leftResult, rightResult);
	}
	
	static long queryMin(int node, int start, int end, int left, int right) {
		if (right < start || end < left) return INF;
		
		if (left <= start && end <= right) return minTree[node];
		
		int mid = (start + end) / 2;
		long leftResult = queryMin(node * 2, start, mid, left, right);
		long rightResult = queryMin(node * 2 + 1, mid + 1, end, left, right);
		
		return Math.min(leftResult, rightResult);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = nextInt(); int M = nextInt();
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) arr[i] = nextInt();
		
		maxTree = new long[4 * N];
		minTree = new long[4 * N];
		initMaxTree(1, 1, N);
		initMinTree(1, 1, N);
		
		for (int i = 0; i < M; i++) {
			int a = nextInt(); int b = nextInt();
			long min = queryMin(1, 1, N, a, b);
			long max = queryMax(1, 1, N, a, b);
			sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.println(sb);	
	}
}
