#include <bits/stdc++.h>
using namespace std;

int a[104][104], visited[104][104];
int N, M, y, x, ny, nx;
const int dy[] = {-1, 0, 1, 0};
const int dx[] = {0, 1, 0, -1};
string str;
queue<pair<int, int> > q;

int main(){
    ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++){
        cin >> str;
        for (int j = 0; j < M; j++){
            a[i][j] = str[j] - '0';
        }
    }

    visited[0][0] = 1;
    q.push({0, 0});
    while (q.size()){
        tie(y, x) = q.front();
        q.pop();
        for (int dir = 0; dir < 4; dir++){
            ny = y + dy[dir];
            nx = x + dx[dir];
            if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]) 
                continue;
            if (a[ny][nx] == 0) continue;
            visited[ny][nx] = visited[y][x] + 1;
            q.push({ny, nx});
        }
    }

    cout << visited[N - 1][M - 1] << '\n';

    return 0;
}