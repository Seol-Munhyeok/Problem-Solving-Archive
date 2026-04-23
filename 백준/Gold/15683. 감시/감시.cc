#include <bits/stdc++.h>
using namespace std;

int N, M, ret = 1e9, a[10][10];
const int dy[] = {-1, 0, 1, 0};
const int dx[] = {0, 1, 0, -1};
vector<pair<int, int>> v;  // cctv의 위치 저장

vector<pair<int, int>> extendCCTV(int here, int dir){
    vector<pair<int, int>> changed;
    int y = v[here].first;
    int x = v[here].second;
    if (a[y][x] == 1){
        while (true){
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (ny >= 0 && ny < N && nx >= 0 && nx < M && a[ny][nx] != 6){
                if (a[ny][nx] == 0){
                    a[ny][nx] = 8;
                    changed.push_back({ny, nx});
                }
                y = ny;
                x = nx;
            } 
            else break;
        }
    } else if (a[y][x] == 2){
        for (int i = 0; i <= 2; i += 2){
            // 한 쪽으로 감시다하고, 다시 처음 cctv 위치로 돌아오기위해 사용
            int _y = y;
            int _x = x;
            while (true){
                int ny = _y + dy[(dir + i) % 4];
                int nx = _x + dx[(dir + i) % 4];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && a[ny][nx] != 6){
                    if (a[ny][nx] == 0){
                        a[ny][nx] = 8;
                        changed.push_back({ny, nx});
                    }
                    _y = ny;
                    _x = nx;
                } 
                else break;
            }
        }
    } else if (a[y][x] == 3){
        for (int i = 0; i < 2; i++){
            int _y = y;
            int _x = x;
            while (true){
                int ny = _y + dy[(dir + i) % 4];
                int nx = _x + dx[(dir + i) % 4];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && a[ny][nx] != 6){
                    if (a[ny][nx] == 0){
                        a[ny][nx] = 8;
                        changed.push_back({ny, nx});
                    }
                    _y = ny;
                    _x = nx;
                } 
                else break;
            }
        }
    } else if (a[y][x] == 4){
        for (int i = 0; i < 3; i++){
            int _y = y;
            int _x = x;
            while (true){
                int ny = _y + dy[(dir + i) % 4];
                int nx = _x + dx[(dir + i) % 4];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && a[ny][nx] != 6){
                    if (a[ny][nx] == 0){
                        a[ny][nx] = 8;
                        changed.push_back({ny, nx});
                    }
                    _y = ny;
                    _x = nx;
                } 
                else break;
            }
        }
    }
    else if (a[y][x] == 5){
        for (int i = 0; i < 4; i++){
            int _y = y;
            int _x = x;
            while (true){
                int ny = _y + dy[(dir + i) % 4];
                int nx = _x + dx[(dir + i) % 4];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && a[ny][nx] != 6){
                    if (a[ny][nx] == 0){
                        a[ny][nx] = 8;
                        changed.push_back({ny, nx});
                    }
                    _y = ny;
                    _x = nx;
                } 
                else break;
            }
        }
    }
    return changed;
}

void dfs(int here){
    // here : 현재 탐색 중인 cctv
    if (here == v.size()){
        int cnt = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (a[i][j] == 0) cnt++;
            }
        }
        ret = min(ret, cnt);
        return;
    }
    for (int k = 0; k < 4; k++){
        vector<pair<int, int>> changed = extendCCTV(here, k);  // k 방향으로 탐색
        dfs(here + 1);  // 다음 cctv 체크
        for (auto b : changed) a[b.first][b.second] = 0;  // 원상복구
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            cin >> a[i][j];
            if (a[i][j] != 6 && a[i][j] != 0) v.push_back({i, j});
        }
    }
    dfs(0);
    cout << ret << "\n";
    return 0;
}