#include <bits/stdc++.h>
using namespace std;

int N, S, ret, a[25];

void go(int idx, int sum){
    if (idx == N){
        if (sum == S) ret++;
        return;
    }
    go(idx + 1, sum);
    go(idx + 1, sum + a[idx]);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> S;
    for (int i = 0; i < N; i++) cin >> a[i];
    go(0, 0);
    if (S == 0) ret--;  // 공집합은 제외
    cout << ret << "\n";
    return 0;
}