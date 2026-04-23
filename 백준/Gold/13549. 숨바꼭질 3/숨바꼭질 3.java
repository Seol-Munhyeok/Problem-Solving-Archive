import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	static class Node {
		int value, dist;
		Node(int value, int dist) {
			this.value = value;
			this.dist = dist;
		}
	}
	
	static final int INF = 1_000_000_000;
	static final int MAX_N = 100_002;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		// 예외 처리 (목적지가 시작점보다 왼쪽에 있는 경우, -1만 하는 것이 최적)
		if (K <= N) {
			System.out.println(N - K);
			return;
		}
	
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
		int[] minDist = new int[MAX_N];
		Arrays.fill(minDist, INF);
		
		pq.offer(new Node(N, 0));
		minDist[N] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// stale check
			if (cur.dist != minDist[cur.value]) continue;
			
			// 종료 조건
			if (cur.value == K) {
				System.out.println(cur.dist);
				return;
			}
			
			// +1로 이동 (가중치 1)
			int nd = cur.dist + 1;
			int v = cur.value + 1;
			if (v < MAX_N && nd < minDist[v]) {
				pq.offer(new Node(v, nd));
				minDist[v] = nd;
			}
			
			// -1로 이동 (가중치 1)
			nd = cur.dist + 1;
			v = cur.value - 1;
			if (v >= 0 && nd < minDist[v]) {
				pq.offer(new Node(v, nd));
				minDist[v] = nd;
			}
			
			// *2로 이동 (가중치 0)
			nd = cur.dist;
			v = cur.value * 2;
			if (v < MAX_N && nd < minDist[v]) {
				pq.offer(new Node(v, nd));
				minDist[v] = nd;
			}
		}
	}
}
