#include <bits/stdc++.h>
using namespace std;

long long N, M, A, B, C, dist[1004];
const int INF = 1e9;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    fill(dist, dist + N + 1, INF);
    vector<pair<int, int>> adj[1004];  // (노드, 비용)
    for (int i = 0; i < M; i++){
        cin >> A >> B >> C;
        adj[A - 1].push_back({B - 1, C});
    }
    dist[0] = 0;
    bool negative_cycle = false;
    for (int i = 0; i < N; i++){
        for (int here = 0; here < N; here++){
            for (auto there : adj[here]){
                int nxt_node = there.first;
                int nxt_dist = there.second;
                if (dist[here] != INF && dist[here] + nxt_dist < dist[nxt_node]){
                    if (i == N - 1) negative_cycle = true;  // N번째 라운드에서도 값이 갱신된다면 음수 t사이클이 존재
                    dist[nxt_node] = dist[here] + nxt_dist;
                }
            }
        }
    }
    if (negative_cycle) cout << -1 << '\n';
    else {
        for (int i = 1; i < N; i++){
            cout << (dist[i] == INF ? -1 : dist[i]) << '\n';
        }
    }
    return 0;
}