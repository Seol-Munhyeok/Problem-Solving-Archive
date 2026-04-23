#include <bits/stdc++.h>
using namespace std;

int N, ret, a[20][20], dp[20][20][3];
// dp(y좌표, x좌표, 파이프 방향(0 : 가로, 1 : 세로, 2 : 대각선))
// 해당 지점을 끝점으로 하여 해당 방향으로 둘 수 있는 경우의 수

bool check(int y, int x, int dir){
    // 해당 위치에 파이프를 둘 수 있는지 판단
    if (dir == 0 || dir == 1) return a[y][x] == 0;
    else if (dir == 2){
        return a[y][x] == 0 && a[y - 1][x] == 0 && a[y][x - 1] == 0;
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            cin >> a[i][j];
        }
    }
    dp[0][1][0] = 1;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            if (check(i, j + 1, 0)) dp[i][j + 1][0] += dp[i][j][0];
            if (check(i + 1, j + 1, 2)) dp[i + 1][j + 1][2] += dp[i][j][0];

            if (check(i + 1, j, 1)) dp[i + 1][j][1] += dp[i][j][1];
            if (check(i + 1, j + 1, 2)) dp[i + 1][j + 1][2] += dp[i][j][1];

            if (check(i, j + 1, 0)) dp[i][j + 1][0] += dp[i][j][2];
            if (check(i + 1, j, 1)) dp[i + 1][j][1] += dp[i][j][2];
            if (check(i + 1, j + 1, 2)) dp[i + 1][j + 1][2] += dp[i][j][2];
        }
    }
    for (int i = 0; i < 3; i++){
        ret += dp[N - 1][N - 1][i];
    }
    cout << ret <<'\n';
    return 0;
}