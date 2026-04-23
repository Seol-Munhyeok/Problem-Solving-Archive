#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll N, dp[31][31];

ll go(ll h, ll w){
    // 기저사례
    if (h < 0 || w < 0) return 0;
    if (h == 0 && w == 0) return 1;
    // 메모이제이션
    ll &ret = dp[h][w];
    if (ret != -1) return ret;
    // 로직
    return ret = go(h - 1, w) + go(h + 1, w - 1);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    while (cin >> N){
        if (N == 0) break;
        memset(dp, -1, sizeof(dp));
        cout << go(1, N - 1) << '\n';
    }
    return 0;
}