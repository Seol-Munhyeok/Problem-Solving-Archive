import java.util.*;
/**
BFS를 수행하면 각 노드에서 최단거리가 나옴
한번 BFS를 하고 DIST 배열 순회해서 최댓값찾고 그 최댓값의 개수를 찾는다.
시간복잡도 O(V + E + 2V)
*/
class Solution {
    
    List<Integer>[] adj;
    int[] dist;
    int n;
    
    public int solution(int n, int[][] edge) {
        this.n = n;
        adj = makeGraph(edge);
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        bfs();
        
        int maxDist = -1;
        for (int i = 1; i <= n; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist) {
                answer++;
            }
        }
        return answer;
    }
    
    private void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        dist[1] = 0;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : adj[cur]) {
                if (dist[next] != -1) continue;
                q.offer(next);
                dist[next] = dist[cur] + 1;
            }
        }
    }
    
    private List<Integer>[] makeGraph(int[][] edge) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        return adj;
    }
}