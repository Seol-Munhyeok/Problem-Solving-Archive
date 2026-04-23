#include <bits/stdc++.h>
using namespace std;

int N, L, ret;
int a[101][101], b[101][101];

void solve(int a[][101]){
    for (int i = 0; i < N; i++){
        int cnt = 1;
        int j;
        for (j = 0; j < N - 1; j++){
            // 지나온 길의 길이를 누적
            if (a[i][j] == a[i][j + 1]) cnt++;
            // 올라가는 경사로 설치
            else if (a[i][j] + 1 == a[i][j + 1] && cnt >= L) cnt = 1;
            // 내려가는 경사로 설치
            else if (a[i][j] - 1 == a[i][j + 1] && cnt >= 0) cnt = -L + 1;
            // 경사로 설치 불가능한 경우
            else break;
        }
        if (j == N - 1 && cnt >= 0) ret++;
    }
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> L;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            cin >> a[i][j];
            b[j][i] = a[i][j];  // 세로 확인용 대칭 배열 추가
        }
    }
    solve(a); solve(b);
    cout << ret << '\n';
    return 0;
}