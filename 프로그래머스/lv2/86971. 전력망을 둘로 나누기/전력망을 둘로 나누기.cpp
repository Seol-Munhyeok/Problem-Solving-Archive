#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

int solution(int n, vector<vector<int>> wires) {
    vector<vector<int>> graph(n + 1);
    
    for (auto& e : wires) {
        int u = e[0], v = e[1];
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    
    auto countComp = [&](int s, int banU, int banV) {
        vector<int> vis(n + 1, 0);
        queue<int> q; q.push(s); vis[s] = 1;
        int cnt = 0;
        while (!q.empty()) {
            int x = q.front(); q.pop(); cnt++;
            for (int y : graph[x]) {
                if ((x == banU && y == banV) || (x == banV && y == banU)) continue;
                if (!vis[y]) q.push(y); vis[y] = 1;
            }
        }
        return cnt;
    };
    
    int answer = 0x3f3f3f3f;
    for (auto& e : wires) {
        int a = e[0], b = e[1];
        int c1 = countComp(a, a, b);
        int c2 = n - c1;
        answer = min(answer, abs(c1 - c2));
    }
    return answer;
}