#include <bits/stdc++.h>
using namespace std;

int n, k, tmp, dp[10002];

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n >> k;
    memset(dp, 0, sizeof(dp));
    dp[0] = 1;
    for (int i = 1; i <= n; i++){
        cin >> tmp;
        for (int j = tmp; j <= k; j++){
            dp[j] += dp[j - tmp];
        }
    }
    cout << dp[k] << "\n";
    return 0;
}