#include <bits/stdc++.h>
using namespace std;

int T, N, M, A, B, visited[1004], cnt;
vector<int> adj[1004];

void dfs(int here){
    visited[here] = 1;
    for (int there : adj[here]){
        if (!visited[there]) dfs(there);
    }
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> T;
    while (T--){
        for (int i = 0; i < 1004; i++) adj[i].clear();
        memset(visited, 0, sizeof(visited));
        cnt = 0;
        cin >> N >> M;
        for (int i = 0; i < M; i++){
            cin >> A >> B;
            adj[A].push_back(B);
            adj[B].push_back(A);
        }
        for (int i = 1; i <= N; i++){
            if (!visited[i]) {
                dfs(i);
                cnt++;
            }
        }
        if (M == N - 1 && cnt == 1) cout << "tree\n";
        else cout << "graph\n";
    }
    return 0;
}