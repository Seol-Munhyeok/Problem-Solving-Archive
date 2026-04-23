#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll N, M, mx, sum, lo, hi, ret, a[100004];

bool check(ll mid){
    if (mx > mid) return false;
    ll cnt = 1, tmp = mid;
    for (int i = 0; i < N; i++){
        if (mid - a[i] < 0){
            cnt++;
            mid = tmp;
        }
        mid -= a[i];
    }
    return cnt <= M;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++){
        cin >> a[i];
        mx = max(mx, a[i]);
        sum += a[i];
    }
    lo = mx; hi = sum;
    while (lo <= hi){
        ll mid = (lo + hi) / 2;
        if (check(mid)){
            hi = mid - 1;
            ret = mid;
        }
        else lo = mid + 1;
    }
    cout << ret << '\n';
    return 0;
}