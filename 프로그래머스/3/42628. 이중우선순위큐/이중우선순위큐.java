import java.util.*;
class Solution {
    
    static class Node {
        int value;
        int id;
        
        Node(int value, int id) {
            this.value = value;
            this.id = id;
        }
    }
    
    void clean(PriorityQueue<Node> pq, boolean[] deleted) {
        while (!pq.isEmpty() && deleted[pq.peek().id]) {
            pq.poll();
        }
    }
    
    public int[] solution(String[] operations) {
        PriorityQueue<Node> minPQ =
            new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
        PriorityQueue<Node> maxPQ =
            new PriorityQueue<>((a, b) -> Integer.compare(b.value, a.value));
        boolean[] deleted = new boolean[operations.length];
        
        int idx = 0;
        for (String operation : operations) {
            if (operation.charAt(0) == 'I') {
                int num = Integer.parseInt(operation.substring(2));
                Node node = new Node(num, idx++);
                minPQ.offer(node);
                maxPQ.offer(node);
                
            } else if (operation.equals("D 1")) {
                clean(maxPQ, deleted);
                
                if (!maxPQ.isEmpty()) {
                    deleted[maxPQ.peek().id] = true;
                    maxPQ.poll();
                }
                
            } else {
                clean(minPQ, deleted);
                
                if (!minPQ.isEmpty()) {
                    deleted[minPQ.peek().id] = true;
                    minPQ.poll();
                }
            }   
        }
        
        clean(maxPQ, deleted);
        clean(minPQ, deleted);
        
        if (maxPQ.isEmpty()) {
            return new int[] {0, 0};
        }
        
        return new int[] {maxPQ.peek().value, minPQ.peek().value};
    }
}