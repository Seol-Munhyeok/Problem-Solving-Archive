#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll S, C, ret, sum, L[1000004];

bool check(ll mid){
    ll cnt = 0;
    for (int i = 0; i < S; i++){
        cnt += (L[i] / mid);
    }
    return cnt >= C;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> S >> C;
    for (int i = 0; i < S; i++) {
        cin >> L[i];
        sum += L[i];
    }
    ll lo = 1, hi = 1e9;
    while (lo <= hi){
        ll mid = (lo + hi) / 2;
        if (check(mid)){
            ret = mid;
            lo = mid + 1;
        }
        else hi = mid - 1;
    }
    cout << (sum - (ret * C)) << '\n';
    return 0;
}