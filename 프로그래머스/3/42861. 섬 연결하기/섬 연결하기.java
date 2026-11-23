import java.util.*;
class Solution {
    
    int[] parent;
    List<Edge> edges;
    
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        edges = new ArrayList<Edge>();
        for (int[] cost : costs) {
            edges.add(new Edge(cost[0], cost[1], cost[2]));
        }
        edges.sort((a, b) -> Integer.compare(a.weight, b.weight));
        
        int totalWeight = 0;
        int count = 0;
        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                totalWeight += edge.weight;
                count++;
            }
            
            if (count == n - 1) break;
        }
        
        return totalWeight;
    }
    
    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    private boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a == b) return false;
        
        parent[b] = a;
        return true;
    }
    
    static class Edge {
        int from, to, weight;
        
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    
}