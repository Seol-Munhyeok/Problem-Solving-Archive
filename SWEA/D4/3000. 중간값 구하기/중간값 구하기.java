import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static final int MOD = 20171109;
	static PriorityQueue<Integer> minHeap, maxHeap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			minHeap = new PriorityQueue<>();  // 큰 값 절반 저장
			maxHeap = new PriorityQueue<>(Collections.reverseOrder());  // 작은 값 절반 저장
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			maxHeap.add(A);
			
			int answer = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				doQuery(X);
				doQuery(Y);
				
				answer = (answer + maxHeap.peek()) % MOD;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static void doQuery(int x) {
		// 1. 일단 작은 절반 후보에 넣음
		maxHeap.add(x);
		
		// 2. maxHeap의 최댓값은 큰 절반으로 넘김
		minHeap.add(maxHeap.poll());
		
		// 3. maxHeap이 중간값을 가지도록 크기 조정
		if (maxHeap.size() < minHeap.size()) {
			maxHeap.add(minHeap.poll());
		}
	}
}
