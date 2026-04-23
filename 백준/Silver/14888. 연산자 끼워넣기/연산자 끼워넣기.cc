#include <bits/stdc++.h>
using namespace std;

int N, a[12], b[4], w, x, y, z;
int mn = 1000000001;
int mx = -1000000001;

void go(int idx, int cur, int plus, int minus, int multi, int divide){
    if (idx == N - 1){
        mn = min(mn, cur);
        mx = max(mx, cur);
        return;
    }
    if (plus) go(idx + 1, cur + a[idx + 1], plus - 1, minus, multi, divide);
    if (minus) go(idx + 1, cur - a[idx + 1], plus, minus - 1, multi, divide);
    if (multi) go(idx + 1, cur * a[idx + 1], plus, minus, multi - 1, divide);
    if (divide) go(idx + 1, cur / a[idx + 1], plus, minus, multi, divide - 1);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++){
        cin >> a[i];
    }
    cin >> w >> x >> y >> z;
    go(0, a[0], w, x, y, z);
    cout << mx << '\n' << mn << '\n';
    return 0;
}