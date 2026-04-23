#include <bits/stdc++.h>
using namespace std;

int N, M, ny, nx, ret = -1;
int a[10][10], b[10][10], visited[10][10];
const int dy[] = {-1, 0, 1, 0};
const int dx[] = {0, 1, 0, -1};
vector<pair<int, int>> v;

void dfs(int y, int x){
    visited[y][x] = 1;
    for (int dir = 0; dir < 4; dir++){
        ny = y + dy[dir];
        nx = x + dx[dir];
        if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
        if (!visited[ny][nx] && a[ny][nx] == 0) {
            a[ny][nx] = 1; dfs(ny, nx);
        } 
    }
    return;
}

int go(){
    int cnt = 0;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            if (a[i][j] == 2) dfs(i, j);
        }
    }
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            if (a[i][j] == 0) cnt++;
        }
    }
    return cnt;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            cin >> a[i][j];
            if (a[i][j] == 0) v.push_back({i, j});
        }
    }
    copy(&a[0][0], &a[0][0]+100, &b[0][0]);
    for (int i = 0; i < v.size(); i++){
        for (int j = 0; j < i; j++){
            for (int k = 0; k < j; k++){
                memset(visited, 0, sizeof(visited));
                a[v[i].first][v[i].second] = 1;
                a[v[j].first][v[j].second] = 1;
                a[v[k].first][v[k].second] = 1;
                ret = max(ret, go());
                copy(&b[0][0], &b[0][0]+100, &a[0][0]);
            }
        }
    }
    cout << ret << '\n';
    return 0;
}