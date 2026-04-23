#include <bits/stdc++.h>
using namespace std;

int R, C, T, ret, a[52][52], visited[52][52];
// 반시계 방향 순서
int dy1[] = {0, -1, 0, 1};
int dx1[] = {1, 0, -1, 0};
// 시계 방향 순서
int dy2[] = {0, 1, 0, -1};
int dx2[] = {1, 0, -1, 0};
vector<pair<int, int>> v1, v2;

vector<pair<int, int>> getOrder(int sy, int sx, int dy[], int dx[]){
    // 공기청정기 위치부터 반시계/시계로 도는 좌표의 순서를 반환한다.  
    vector<pair<int, int>> v;
    int cnt = 0;
    int y = sy; int x = sx;
    while (true){
        int ny = y + dy[cnt];
        int nx = x + dx[cnt];
        if (ny == sy && nx == sx) break;
        if (ny < 0 || ny >= R || nx < 0 || nx >= C){
            cnt++;
            ny = y + dy[cnt];
            nx = x + dx[cnt];
        }
        if (ny == sy && nx == sx) break;
        y = ny; x = nx;
        v.push_back({ny, nx});
    }
    return v;
}

void go(vector<pair<int, int>> &v){
    // v에 담긴 순서에 따라 값을 이동.
    for (int i = v.size() - 1; i > 0; i--){
        a[v[i].first][v[i].second] = a[v[i - 1].first][v[i - 1].second];
    }
    a[v[0].first][v[0].second] = 0;
    return;
}

void spread(int dy[], int dx[]){
    // 미세먼지 확산
    memset(visited, 0, sizeof(visited));
    queue<pair<int, int>> q;
    for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
            if (a[i][j] != -1 && a[i][j]) q.push({i, j});
        }
    }
    while (q.size()){
        int y, x;
        tie(y, x) = q.front(); q.pop();
        int amount = a[y][x] / 5;
        for (int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny >= R || nx < 0 || nx >= C || a[ny][nx] == -1) continue;
            visited[ny][nx] += amount;
            visited[y][x] -= amount;
        }
    }
    for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
            a[i][j] += visited[i][j];
        }
    }
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> R >> C >> T;
    bool flag = 1;
    for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
            cin >> a[i][j];
            if (a[i][j] == -1){
                if (flag){
                    v1 = getOrder(i, j, dy1, dx1);
                    flag = false;
                }
                else v2 = getOrder(i, j, dy2, dx2);
            }
        }
    }
    while (T--){
        spread(dy1, dx2);
        go(v1);
        go(v2);
    }
    for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
            if (a[i][j] != -1) ret += a[i][j];
        }
    }
    cout << ret << '\n';
    return 0;
}