#include <bits/stdc++.h>
using namespace std;

int N, M, S, E, a[2002], dp[2002][2002];

int go(int s, int e){
    // 기저사례
    if (e - s == 1) return a[s] == a[e] ? 1 : 0;
    if (s == e) return 1;
    if (a[s] != a[e]) return 0;
    // 메모이제이션
    int &ret = dp[s][e];
    if (ret != -1) return ret;
    // 로직
    return ret = go(s + 1, e - 1);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 1; i <= N; i++) cin >> a[i];
    cin >> M;
    memset(dp, -1, sizeof(dp));
    while (M--){
        cin >> S >> E;
        cout << go(S, E) << "\n";
    }
    return 0;
}