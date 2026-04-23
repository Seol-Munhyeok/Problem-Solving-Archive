#include <bits/stdc++.h>
using namespace std;

long long N, M, A, B, C, w[1004];
const int INF = 1e9;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    fill(w, w + N + 1, INF);  // 가중치 배열 무한대로 초기화
    //   (도착 노드, 비용)  출발 노드
    vector<pair<int, int>> adj[1004];
    for (int i = 0; i < M; i++){
        cin >> A >> B >> C;
        adj[A - 1].push_back({B - 1, C});
    }
    w[0] = 0;  // 0번 노드에서 출발
    bool negative_cycle = false;
    for (int i = 0; i < N; i++){
        for (int here = 0; here < N; here++){
            for (auto there : adj[here]){
                int nxt_node = there.first;
                int nxt_w = there.second;
                if (w[here] != INF && w[here] + nxt_w < w[nxt_node]){
                    w[nxt_node] = w[here] + nxt_w;
                    // N번째 라운드에서도 값이 갱신되면, 음수 사이클이 존재한다는 뜻
                    if (i == N - 1) negative_cycle = true;
                }
            }
        }
    }
    if (negative_cycle) cout << -1 << "\n";
    else {
        for (int i = 1; i < N; i++){
            cout << (w[i] == INF ? -1 : w[i]) << "\n";
        }
    }
    return 0;
}