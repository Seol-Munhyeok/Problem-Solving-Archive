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
	
	static final int MAX_MEMORY = 100_000;
	
	// var -> 시작 주소
	static Map<String, Integer> addressMap = new HashMap<>();
	// var -> 해당 구간 노드
	static Map<String, Node> nodeMap = new HashMap<>();
	// 할당된 메모리의 구간을 연결리스트로 저장
	static LinkedList allocatedList = new LinkedList();
	
	static class Node {
		int start;
		int end;
		Node prev;
		Node next;
		
		Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	static class LinkedList {
		Node head;
		Node tail;
		
		LinkedList() {
			head = new Node(-1, -1);
			tail = new Node(MAX_MEMORY + 1, MAX_MEMORY + 1);
			
			head.next = tail;
			tail.prev = head;
		}
	
	
		void insertBefore(Node nextNode, Node newNode) {
			Node prevNode = nextNode.prev;
			
			newNode.prev = prevNode;
			newNode.next = nextNode;
			prevNode.next = newNode;
			nextNode.prev = newNode;
		}
		
		void remove(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
	}
	
	static void malloc(String var, int size) {
		int candidateStart = 1;  // 지금 검사 중인 빈 공간의 시작 주소
		Node nextNode = allocatedList.head.next;
		
		while (candidateStart + size - 1 <= MAX_MEMORY) {
			int gapSize = nextNode.start - candidateStart;
			
			if (gapSize >= size) {
				Node newNode = new Node(candidateStart, candidateStart + size - 1);
				allocatedList.insertBefore(nextNode, newNode);
				
				addressMap.put(var, candidateStart);
				nodeMap.put(var, newNode);
				return;
			}
			
			if (nextNode == allocatedList.tail) break;
			
			candidateStart = nextNode.end + 1;
			nextNode = nextNode.next;
		}
		
		addressMap.put(var, 0);  // 넣을 자리가 없음 -> 0 할당
	}
	
	static void free(String var) {
		Integer address = addressMap.get(var);
		if (address == null || address == 0) return;
		
		Node node = nodeMap.get(var);
		allocatedList.remove(node);
		
		addressMap.put(var, 0);
		nodeMap.remove(var);
	}
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = nextInt();
		for (int i = 0; i < N; i++) {
			String cmd = next();
			
			if (cmd.charAt(0) == 'f') {
				String var = cmd.substring(5, 9);
				free(var);
			}
			else if (cmd.charAt(0) == 'p') {
				String var = cmd.substring(6, 10);
				sb.append(addressMap.getOrDefault(var, 0)).append("\n");
			}
			else {
				String var = cmd.substring(0, 4);
				int s = cmd.indexOf('(');
				int e = cmd.indexOf(')');
				int size = Integer.parseInt(cmd.substring(s + 1, e));
				malloc(var, size);
			}
		}
		
		System.out.println(sb);
	}
}
