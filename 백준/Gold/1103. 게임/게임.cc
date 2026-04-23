#include <bits/stdc++.h>
using namespace std;

int N, M, res, a[52][52], visited[52][52], dp[52][52];
const int dy[] = {-1, 0, 1, 0};
const int dx[] = {0, 1, 0, -1};
string s;

int dfs(int y, int x){
    // 기저사례
    if (y < 0 || y >= N || x < 0 || x >= M || a[y][x] == 0) return 0;
    if (visited[y][x]) { cout << -1 << '\n'; exit(0); }  // 재방문 = 사이클 존재
    // 메모이제이션
    int &ret = dp[y][x];
    if (ret != -1) return ret;
    // 로직
    int dist = a[y][x];
    visited[y][x] = 1;  // 움직여보고
    dp[y][x] = 0;
    for (int i = 0; i < 4; i++){
        int ny = y + (dy[i] * dist);
        int nx = x + (dx[i] * dist);
        ret = max(ret, dfs(ny, nx) + 1);
    }
    visited[y][x] = 0;  // 원상복구
    return ret;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++){
        cin >> s;
        for (int j = 0; j < M; j++){
            a[i][j] = (s[j] == 'H') ? 0 : (s[j] - '0');
        }
    }
    memset(dp, -1, sizeof(dp));
    cout << dfs(0, 0) << '\n';
    return 0;
}