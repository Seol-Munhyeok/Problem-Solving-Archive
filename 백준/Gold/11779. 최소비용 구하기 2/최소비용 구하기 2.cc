#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second 

int V, E, st, en;
vector<pair<int, int>> adj[1005];   // {비용, 정점 번호}
const int INF = 1e9;
int d[1005];  // 최단 거리 테이블
int pre[1005];  // 경로 추적용 테이블

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> V >> E;
    fill(d, d + V + 1, INF);
    while (E--){
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({w, v});
    }
    cin >> st >> en;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    d[st] = 0;
    // 우선순위 큐에 (0, 시작점) 추가
    pq.push({d[st], st});
    while (!pq.empty()){
        auto cur = pq.top();  pq.pop();  // {비용, 정점 번호}
        // 거리가 d에 있는 값과 다를 경우 넘어감
        if (d[cur.Y] != cur.X) continue;
        for (auto nxt : adj[cur.Y]){
            if (d[nxt.Y] > d[cur.Y] + nxt.X){
                // cur를 거쳐가는 것이 더 작은 값을 가질 경우
                // d[nxt_idx]을 갱신하고 우선순위 큐에 (거리, nxt.Y)를 추가
                d[nxt.Y] = d[cur.Y] + nxt.X;
                pq.push({d[nxt.Y], nxt.Y});
                pre[nxt.Y] = cur.Y;
            }
        }
    }
    cout << d[en] << "\n";
    vector<int> path;
    int cur = en;
    while (cur != st){
        path.push_back(cur);
        cur = pre[cur];
    }
    path.push_back(cur);
    reverse(path.begin(), path.end());
    cout << path.size() << "\n";
    for (auto x : path) cout << x << " "; 
    return 0;
}