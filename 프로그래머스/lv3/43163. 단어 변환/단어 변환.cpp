#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

bool oneDiff(const string& a, const string& b) {
    int diff = 0;
    for (int i = 0; i < a.size(); i++) {
        if (a[i] != b[i]) diff++;
        if (diff >= 2) return false;
    }
    return diff == 1;
}

int solution(string begin, string target, vector<string> words) {
    // target이 사전에 없으면 불가능
    if (find(words.begin(), words.end(), target) == words.end()) return 0;
    
    // 그래프 만들기
    unordered_map<string, vector<string>> graph;
    int n = words.size();
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            string a = words[i], b = words[j];
            if (oneDiff(a, b)) {
                graph[a].push_back(b);
                graph[b].push_back(a);
            }
        }
    }
    
    // begin -> words 간 간선
    for (auto& w : words) {
        if (oneDiff(begin, w)) {
            graph[begin].push_back(w);
            graph[w].push_back(begin);
        }
    }
    
    // BFS로 최단 경로 찾기
    unordered_set<string> vis;
    queue<pair<string, int>> q;
    q.push({begin, 0});
    vis.insert(begin);
    
    while (!q.empty()) {
        auto [cur, d] = q.front(); q.pop();
        if (cur == target) return d;
        for (auto& nxt : graph[cur]) {
            if (!vis.count(nxt)) {
                q.push({nxt, d + 1});
                vis.insert(nxt);
            }
        }
    }
    
    return 0;
}