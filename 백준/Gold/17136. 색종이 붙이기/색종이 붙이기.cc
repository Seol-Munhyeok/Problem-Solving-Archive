#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int a[20][20], visited[20][20], ret = INF;
int paper[5] = {5, 5, 5, 5, 5};  // 각 크기의 색종이의 개수를 저장 

bool check(int y, int x, int len){
    for (int i = y; i < y + len; i++){
        for (int j = x; j < x + len; j++){
            if (i >= 10 || j >= 10) return false;
            if (a[i][j] == 0 || visited[i][j]) return false;
        }
    }
    return true;
}

void attach(int y, int x, int len){
    for (int i = y; i < y + len; i++){
        for (int j = x; j < x + len; j++){
            visited[i][j] = 1;
        }
    }
}

void detach(int y, int x, int len){
    for (int i = y; i < y + len; i++){
        for (int j = x; j < x + len; j++){
            visited[i][j] = 0;
        }
    }
}

void go(int y, int x, int cnt){
    // 기저사례 : 모든 행과 열을 확인한 경우
    if (y == 10){
        ret = min(ret, cnt);
        return;
    }
    // 색종이를 못 붙이거나, 이미 색종이가 붙어 있는 경우 다음 위치로 이동
    if (a[y][x] == 0 || visited[y][x]){
        int ny = y;
        int nx = x + 1;
        if (nx == 10) {
            nx = 0;
            ny++;
        }
        go(ny, nx, cnt);
        return;
    }
    // 모든 길이의 색종이를 붙여보기
    for (int len = 5; len >= 1; len--){
        if (paper[len - 1] && check(y, x, len)){
            // 색종이 붙이고 다음 위치로 이동
            attach(y, x, len);
            int nx = x + len;
            int ny = y;
            if (nx == 10) {
                nx = 0;
                ny++;
            }
            paper[len - 1]--;
            go(ny, nx, cnt + 1);
            // 색종이를 제거하고 visited 배열 초기화
            detach(y, x, len);
            paper[len - 1]++;
        }
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    for (int i = 0; i < 10; i++){
        for (int j = 0; j < 10; j++){
            cin >> a[i][j];
        }
    }
    go(0, 0, 0);
    cout << (ret == INF ? -1 : ret) << "\n";
    return 0;
}