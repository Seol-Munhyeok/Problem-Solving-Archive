#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll N, a[102], dp[102][21];
// dp[i][j] = i번째 수까지 고려했을 때 숫자 j를 만들 수 있는 경우의 수

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 1; i <= N; i++) cin >> a[i];
    dp[1][a[1]] = 1;
    for (int i = 2; i <= N - 1; i++){
        for (int j = 0; j <= 20; j++){
            if (dp[i - 1][j]){
                if (j + a[i] <= 20) dp[i][j + a[i]] += dp[i - 1][j];  // 더하는 경우
                if (j - a[i] >= 0) dp[i][j - a[i]] += dp[i - 1][j];  // 빼는 경우
            }
        }
    }
    cout << dp[N - 1][a[N]] << "\n";
    return 0;
}