import java.util.*;
/**
트리 구조
노드 구성 (직원번호(1based), 해당 직원의 하루 평균 매출액)
CEO = 루트(1번, 팀장), 팀장 = parent, 팀원 = child
트리를 팀 단위로 나눠야 함
    한 직원은 최대 2개의 팀에 소속될 수 있다.
    어떤 직원이 두 개의 팀에 소속되어 있다면, 반드시 하나의 팀에서는 팀장, 나머지 팀에서는 팀원이어야 함
    팀은 높이가 1이 되도록 하는 각각의 서브트리
각 팀에서 1명 이상의 직원을 선택해서 그 때의 매출액의 합계를 최소화. 해당 최소화된 매출액을 리턴
두 팀에 모두 속해있으면 두 팀에 모두 참석한 것으로 인정

dp[i][0] = i가 참석하지 않을 때, i를 루트로 하는 서브트리의 최소 비용
dp[i][1] = i가 참석할 때, i를 루트로 하는 서브트리의 최소 비용

1) 서브트리의 루트노드가 참석하고, 서브트리의 자식 노드가 참석하지 않을 경우
2) 서브트리의 루트노드가 참석하고, 서브트리의 자식 노드가 참석할 경우
3) 서브트리의 루트노드가 참석하지 않고, 서브트리의 자식 노드가 참석할 경우

*/
class Solution {
    int N;
    int[] sales;  // 0-based
    List<Integer>[] adj;  // 1-based
    int[][] dp;
    
    public int solution(int[] sales, int[][] links) {
        // 자료 초기화
        this.sales = sales;
        N = sales.length;
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        dp = new int[sales.length + 1][2];
        for (int[] link : links) {
            adj[link[0]].add(link[1]);
        }
        // DFS 수행 및 정답 도출
        dfs(1);
        int answer = Math.min(dp[1][0], dp[1][1]);
        return answer;
    }
    
    private void dfs(int cur) {
        // 자식 노드부터 탐색
        for (int child : adj[cur]) {
            dfs(child);    
        }
        
        // 1. 팀장이 워크숍에 참석하는 경우
        dp[cur][1] = sales[cur - 1];
        for (int child : adj[cur]) {
            dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
        }
        
        // 리프노드의 경우 탐색 종료
        // 조기에 종료하지 않으면 minExtra가 갱신되지 않으므로
        if (adj[cur].isEmpty()) {
            dp[cur][0] = 0;
            return;
        }
        
        // 2. 팀장이 워크숍에 불참하는 경우
        int childSum = 0;
        int minExtra = Integer.MAX_VALUE;
        for (int child : adj[cur]) {
            childSum += Math.min(dp[child][0], dp[child][1]);
            minExtra = Math.min(minExtra, dp[child][1] - dp[child][0]);
        }
        dp[cur][0] = childSum + Math.max(0, minExtra);
    }
}