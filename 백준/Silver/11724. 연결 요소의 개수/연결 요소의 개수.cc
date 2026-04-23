#include <bits/stdc++.h>
using namespace std;

int N, M, u, v, ret;
bool visited[1002];
queue<int> q;
vector<int> adj[1002];

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < M; i++){
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    for (int i = 1; i <= N; i++){
        if (visited[i]) continue;
        q.push(i);
        visited[i] = true;
        ret++;
        while (q.size()){
            int cur = q.front();
            q.pop();
            for (auto nxt : adj[cur]){
                if (visited[nxt]) continue;
                q.push(nxt);
                visited[nxt] = true;
            }
        }
    }
    cout << ret << "\n";
    return 0;
}