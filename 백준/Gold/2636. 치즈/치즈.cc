#include <bits/stdc++.h>
using namespace std;
#define prev ffff

int a[104][104], prev[104][104], visited[104][104], N, M, ret;
const int dy[] = {-1, 0, 1, 0};
const int dx[] = {0, 1, 0, -1};

void dfs(int y, int x){
    visited[y][x] = 1;
    for (int i = 0; i < 4; i++){
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || a[ny][nx] == 1)
            continue;
        dfs(ny, nx);
    }
    return; 
}

void go(){
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            if (visited[i][j] == 1){
                for (int d = 0; d < 4; d++){
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    if (visited[i][j] == 1 || a[ny][nx] == 1) a[ny][nx] = 0;
                }
            }
        }
    }
    return;
}

bool finished(){
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            if (a[i][j] == 1) return false;
        }
    }
    return true;
}

int check(){
    int cnt = 0;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            if (prev[i][j] == 1) cnt++;
        }
    }
    return cnt;
}

int main(){
    cin >> N >> M;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            cin >> a[i][j];
        }
    }
    while (!finished()){
        copy(&a[0][0], &a[0][0] + 100 * 100, &prev[0][0]);
        memset(visited, 0, sizeof(visited));
        dfs(0, 0);
        go();
        ret++;
    }
    cout << ret << '\n';
    cout << check() << '\n';
    return 0;
}