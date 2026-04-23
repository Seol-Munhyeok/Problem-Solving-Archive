#include <bits/stdc++.h>
using namespace std;

const int dy[] = {0, -1, 0, 1};
const int dx[] = {-1, 0, 1, 0};
int N, M, a[52][52], visited[52][52], cnt, ret1, ret2, ret3;
vector<int> v;

void dfs(int y, int x, int& cnt){
    visited[y][x] = 1;
    cnt++;
    for (int i = 0; i < 4; i++){
        // 탐색순서 : 서(0001) → 북(0010) → 동(0100) → 남(1000)
        if (a[y][x] & (1 << i)) continue;
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || ny >= M || nx < 0 || nx >= N || visited[ny][nx]) continue;
        dfs(ny, nx, cnt);
    }
    return;
}

void go(int y, int x){
    for (int idx = 0; idx < 4; idx++){
        if (!visited[y][x] && (a[y][x] & (1 << idx))) {
            memset(visited, 0, sizeof(visited));
            a[y][x] &= ~(1 << idx);
            cnt = 0;
            dfs(y, x, cnt);
            v.push_back(cnt);
            a[y][x] |= (1 << idx);
        }
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < M; i++){
        for (int j = 0; j < N; j++){
            cin >> a[i][j];
        }
    }
    for (int i = 0; i < M; i++){
        for (int j = 0; j < N; j++){
            if (!visited[i][j]) {
                ret1++; 
                cnt = 0;
                dfs(i, j, cnt);
                ret2 = max(ret2, cnt);
            } 
        }
    }
    for (int i = 0; i < M; i++){
        for (int j = 0; j < N; j++){
            memset(visited, 0, sizeof(visited));
            go(i, j);
        }
    }
    ret3 = *max_element(v.begin(), v.end());
    cout << ret1 << '\n' << ret2 << '\n' << ret3 << '\n';
    
    return 0;
}