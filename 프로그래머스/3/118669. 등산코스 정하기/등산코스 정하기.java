/**
1-n 정점(1-based), 양방향 간선
    정점 = 출입구, 쉼터, 혹은 산봉우리
    간선 가중치 (소요 시간)
    등산 코스 = 정점의 나열
쉼터 혹은 산봉우리를 방문할 때마다 휴식 가능
intensity = 휴식 없이 이동해야 하는 시간 중 가장 긴 시간
출입구 -> 산봉우리(1번만) -> 출입구 등산 코스를 결정
    이러한 규칙을 지키면서 intensity가 최소가 되도록
    출입구는 여러개 있으나 처음 출발한 출입구와 동일한 출입구로 돌아와야하며, 다른 출입구를 방문할 수 없다.
    
Return: intensity가 최소가 되는 등산코스에 포함된 산봉우리 번호와 intensity의 최솟값
    intensity가 최소가 되는 등산코스가 여러 개라면 그중 산봉우리의 번호가 가장 낮은 등산코스를 선택
n(정점) <= 50,000
간선 <= 200,000

[풀이]
출입구, 산봉우리가 아니면 무조건 다 쉼터임
특정 출입구(a), 산봉우리(b)라고 할 때
a -> b 경로 중 최대 간선의 값이 최소가 되도록 하는 경우 찾기
    무방향 그래프이므로 a -> b 경로의 최소 = b -> a 경로의 최소이므로 a -> b만 보면 된다. 
기존 다익스트라는 경로의 합인데 정의를 지나간 간선의 최대 가중치로 정의를 변경
출입구와 산봉우리는 한 번만 지나갈 수 있으므로 조건에 맞지 않는 노드는 pass 하면 됨
    모든 출입구마다 다익스트라를 수행하면 O(gate.length * E log V)로 시간초과
    모든 출입구를 pq에 미리 추가하는 멀티 소스 다익스트라로 풀이
        intensity[summit] = 모든 출입구 중 하나에서 summit까지 가는 최소 intensity
*/
import java.util.*;

class Solution {
    
    boolean[] isGate, isSummit;
    List<Node>[] graph;
    final int INF = 1_000_000_000;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 그래프 만들기
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] e : paths) {
            int i = e[0], j = e[1], w = e[2];
            graph[i].add(new Node(j, w));
            graph[j].add(new Node(i, w));
        }
        
        // 다익스트라 수행
        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];
        for (int gate : gates) isGate[gate] = true;
        for (int summit : summits) isSummit[summit] = true;
        int[] intensity = dijkstra(n, gates);
        
        // 정답 구하기        
        int answerSummit = 0, minIntensity = INF;
        Arrays.sort(summits);
        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                answerSummit = summit;
                minIntensity = intensity[summit];
            }
        }
        
        int[] answer = new int[2];
        answer[0] = answerSummit;
        answer[1] = minIntensity;
        return answer;
    }
    
    private int[] dijkstra(int n, int[] gates) {
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, INF);
        
        PriorityQueue<Node> pq 
            = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new Node(gate, 0));
        }
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (isSummit[cur.v]) continue;
            if (cur.cost > intensity[cur.v]) continue;
            
            for (Node node : graph[cur.v]) {
                int next = node.v;
                if (isGate[next]) continue;
                int nextCost = Math.max(cur.cost, node.cost);
                
                if (nextCost < intensity[next]) {
                    intensity[next] = nextCost;
                    pq.offer(new Node(next, nextCost));
                }  
            }
        }
        
        return intensity;
        
    }
    
    static class Node {
        int v, cost;
        Node (int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}