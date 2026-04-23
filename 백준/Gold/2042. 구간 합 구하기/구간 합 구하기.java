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
	
	static long[] arr;
	static long[] tree;
	
	// [start,end] 구간 합을 계산하여 tree[node]에 저장하고 반환
	static long init(int node, int start, int end) {
		if (start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}
	
	// tree[node]는 arr[start..end]의 합
	// query는 arr[left..right]의 합을 반환
	static long query(int node, int start, int end, int left, int right) {
		
		// 1) 완전 불포함: 겹치는 게 없음
		if (right < start || end < left) return 0;
		
		// 2) 완전 포함: node 구간이 query 구간 안에 통째로 들어감
		if (left <= start && end <= right) return tree[node];
		
		// 3) 부분 겹침: 더 쪼개서 왼쪽/오른쪽 합산
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right) 
			 + query(node * 2 + 1, mid + 1, end, left, right); 
	}
	
	// arr[idx]가 diff만큼 변했을 때, 이를 포함하는 모든 구간 합을 갱신한다.
	static void update(int node, int start, int end, int idx, long diff) {
		
		// 1) idx가 이 구간에 없으면 종료
		if (idx < start || end < idx) return;
		
		// 2) 현재 구간은 영향을 받음
		tree[node] += diff;
		
		// 3) 리프가 아니면 계속 내려감
		if (start != end) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, idx, diff);
			update(node * 2 + 1, mid + 1, end, idx, diff);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = nextInt(); int M = nextInt(); int K = nextInt();
		arr = new long[N + 1];
		for (int i = 1; i <= N; i++) arr[i] = nextLong();
		
		tree = new long[4 * N];
		init(1, 1, N);
		
		for (int i = 0; i < M + K; i++) {
			int a = nextInt(); int b = nextInt();
			if (a == 1) {
				long c = nextLong();
				long diff = c - arr[b];
				arr[b] = c;
				update(1, 1, N, b, diff);
			}
			else {
				int c = nextInt();
				sb.append(query(1, 1, N, b, c)).append("\n");
			}
		}
		System.out.println(sb);	
	}
}
