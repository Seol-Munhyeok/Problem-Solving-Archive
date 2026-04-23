#include <bits/stdc++.h>
using namespace std;

int V, E, st;
vector<pair<int, int>> adj[20005];   // {비용, 정점 번호}
const int INF = 1e9 + 10;
int d[20005];  // 최단 거리 테이블

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> V >> E >> st;
    fill(d, d + V + 1, INF);
    while (E--){
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({w, v});
    }
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    d[st] = 0;
    // 우선순위 큐에 (0, 시작점) 추가
    pq.push({d[st], st});
    while (!pq.empty()){
        auto cur = pq.top();  pq.pop();  // {비용, 정점 번호}
        // 거리가 d에 있는 값과 다를 경우 넘어감
        int cur_idx = cur.second, cur_dist = cur.first;
        if (d[cur_idx] != cur_dist) continue;
        for (auto nxt : adj[cur_idx]){
            int nxt_idx = nxt.second, nxt_dist = nxt.first;
            if (d[nxt_idx] > d[cur_idx] + nxt_dist){
                // cur를 거쳐가는 것이 더 작은 값을 가질 경우
                // d[nxt_idx]을 갱신하고 우선순위 큐에 (거리, nxt.Y)를 추가
                d[nxt_idx] = d[cur_idx] + nxt_dist;
                pq.push({d[nxt_idx], nxt_idx});
            }
        }
    }
    for(int i = 1; i <= V; i++){
        if(d[i] == INF) cout << "INF\n";
        else cout << d[i] << "\n";
    }
    return 0;
}