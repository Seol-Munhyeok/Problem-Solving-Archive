#include <bits/stdc++.h>
using namespace std;

int cnt, a[10][10];
bool found, visited[10];
vector<pair<int, int>> v;

bool check(int y, int x){
    // (y, x)에 있는 숫자가 스도쿠 규칙을 만족하는지 체크
    int yy = (y / 3) * 3;  int xx = (x / 3) * 3;
    // 3 * 3 부분 체크
    fill(visited, visited + 10, false);
    for (int i = yy; i < yy + 3; i++){
        for (int j = xx; j < xx + 3; j++){
            if (a[i][j] && visited[a[i][j]]) return false;
            visited[a[i][j]] = true;
        }
    }
    // 가로 체크
    fill(visited, visited + 10, false);
    for (int i = 0; i < 9; i++){
        if (a[y][i] && visited[a[y][i]]) return false;
        visited[a[y][i]] = true;
    }
    // 세로 체크
    fill(visited, visited + 10, false);
    for (int i = 0; i < 9; i++){
        if (a[i][x] && visited[a[i][x]]) return false;
        visited[a[i][x]] = true;
    }
    return true;
}

void dfs(int n){
    // n = 0의 좌표가 저장된 v에서 탐색할 인덱스
    if (n == cnt){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                cout << a[i][j] << " ";
            }
            cout << "\n";
        }
        found = true;
        return;
    }
    int y, x;
    for (int k = 1; k <= 9; k++){
        y = v[n].first;  x = v[n].second;
        a[y][x] = k;
        if (check(y, x)) dfs(n + 1); // 그 숫자가 가능하면 다음 빈칸 탐색
    }
    if (found) return;
    a[y][x] = 0;  // 다 불가능하면 원상복구
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    for (int i = 0; i < 9; i++){
        for (int j = 0; j < 9; j++){
            cin >> a[i][j];
            if (a[i][j] == 0){
                cnt++;
                v.push_back({i, j});
            } 
        }
    }
    dfs(0);
    return 0;
}