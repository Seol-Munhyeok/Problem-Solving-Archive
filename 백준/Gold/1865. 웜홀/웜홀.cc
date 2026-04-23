#include <bits/stdc++.h>
using namespace std;

int TC, N, M, W, S, E, T, dist[6006];
const int INF = 1e9;
vector<pair<int, int>> adj[6006];

void clear(){
    for (int i = 0; i < 6006; i++) adj[i].clear();
}

bool bellmanFord(){
    for (int i = 0; i < N; i++){
        for (int here = 0; here < N; here++){
            for (auto there : adj[here]){
                int nxt_node = there.first;
                int nxt_dist = there.second;
                if (dist[here] + nxt_dist < dist[nxt_node]){
                    if (i == N - 1) return true;
                    dist[nxt_node] = dist[here] + nxt_dist;
                }
            }
        }
    }
    return false;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> TC;
    while (TC--){
        fill(dist, dist + 6006, INF);
        dist[0] = 0;
        clear();
        cin >> N >> M >> W;
        while (M--){
            cin >> S >> E >> T;
            adj[S - 1].push_back({E - 1, T});
            adj[E - 1].push_back({S - 1, T});
        }
        while (W--){
            cin >> S >> E >> T;
            adj[S - 1].push_back({E - 1, -T});
        }
        cout << (bellmanFord() ? "YES" : "NO") << '\n';
    }
    return 0;
}