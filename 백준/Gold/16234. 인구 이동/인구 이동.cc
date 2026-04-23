#include <bits/stdc++.h>
using namespace std;

int N, L, R, sum, cnt, a[54][54], visited[54][54];
const int dy[] = {-1, 0, 1, 0};
const int dx[] = {0, 1, 0, -1};
vector<pair<int, int>> v;

void dfs(int y, int x, vector<pair<int, int>> &v){
    for (int i = 0; i < 4; i++){
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx]) continue;
        if (abs(a[ny][nx] - a[y][x]) >= L && abs(a[ny][nx] - a[y][x]) <= R){
            visited[ny][nx] = 1;
            v.push_back({ny, nx});
            sum += a[ny][nx];
            dfs(ny, nx, v);
        }
    }
}

int main(){
    cin >> N >> L >> R;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            cin >> a[i][j];
        }
    }

    while (true){
        bool flag = 0;
        fill(&visited[0][0], &visited[0][0] + 54 * 54, 0);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (!visited[i][j]){
                    v.clear();
                    visited[i][j] = 1;
                    v.push_back({i, j});
                    sum = a[i][j];
                    dfs(i, j, v);
                    if (v.size() == 1) continue;
                    for (pair<int, int> b : v){
                        a[b.first][b.second] = sum / v.size();
                        flag = 1;
                    }
                }
            }
        }
        if (!flag) break;
        cnt++;
    }
    cout << cnt << '\n';
    return 0;
}
