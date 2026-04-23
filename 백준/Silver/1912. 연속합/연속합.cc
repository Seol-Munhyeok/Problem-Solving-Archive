#include <bits/stdc++.h>
using namespace std;

// dp 풀이
// n번까지의 연속합의 최대값은 max("n-1번째 까지의 최댓값 + n번 값", "n번 값")
int n, a[100004], dp[100004], ret;
int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    for (int i = 0; i < n; i++){
        cin >> a[i];
    }
    ret = a[0];
    dp[0] = a[0];
    for (int i = 1; i < n; i++){
        dp[i] = max(dp[i - 1] + a[i], a[i]);
        ret = max(dp[i], ret);
    }
    cout << ret <<'\n';
    return 0;
}