// BOJ 1260
#include <bits/stdc++.h>
using namespace std;

int N, M, V, u, v;
bool visited[1002];
vector<int> adj[1002];

void dfs(int here){
    visited[here] = true;
    cout << here << " ";
    for (auto nxt : adj[here]){
        if (visited[nxt]) continue;
        dfs(nxt);
    }
}

void bfs(int here){
    queue<int> q;
    q.push(here);
    while (q.size()){
        visited[here] = true;
        int cur = q.front();
        cout << cur << " ";
        q.pop();
        for (auto nxt : adj[cur]){
            if (visited[nxt]) continue;
            visited[nxt] = true;
            q.push(nxt);
        }
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M >> V;
    while (M--){
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    // 번호가 작은 것 부터 방문하기 위해 정렬
    for (int i = 1; i <= N; i++){
        sort(adj[i].begin(), adj[i].end());
    }
    dfs(V);
    fill(visited, visited + 1002, false);
    cout << "\n";
    bfs(V);
    return 0;
}