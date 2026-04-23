#include <bits/stdc++.h>
using namespace std;

// dp[i] = i를 1로 만들기 위해 필요한 연산의 횟수의 최솟값
int dp[1000005];
int n;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    dp[1] = 0;
    for (int i = 2; i <= n; i++){
        dp[i] = dp[i - 1] + 1;
        if (i % 2 == 0) dp[i] = min(dp[i], dp[i / 2] + 1);
        if (i % 3 == 0) dp[i] = min(dp[i], dp[i / 3] + 1);
    }
    cout << dp[n];
    return 0;
}