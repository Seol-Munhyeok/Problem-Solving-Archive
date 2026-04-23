#include <bits/stdc++.h>
using namespace std;

int dp[1004][2][34], N, M, b[1004];

int go(int idx, int tree, int cnt){
    // 기저사례
    if (cnt < 0) return -1e9;
    if (idx == N) return 0;
    // 메모이제이션
    int &ret = dp[idx][tree][cnt];
    if (~ret) return ret;  // ret != -1 을 ~ret로 구현가능
    // 로직
    return ret = max(go(idx + 1, tree^1, cnt - 1), go(idx + 1, tree, cnt)) + (tree == b[idx] - 1);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    // 초기화
    memset(dp, -1, sizeof(dp));
    cin >> N >> M;
    for (int i = 0; i < N; i++) cin >> b[i];
    cout << max(go(0, 1, M - 1), go(0, 0, M)) << '\n';
    return 0;
}