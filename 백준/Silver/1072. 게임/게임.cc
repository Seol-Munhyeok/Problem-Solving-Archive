#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll X, Y, Z, ret = -1;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> X >> Y;
    Z = Y * 100 / X;
    ll lo = 1, hi = 1e9;
    while (lo <= hi){
        ll mid = (lo + hi) / 2;
        if ((Y + mid) * 100 / (X + mid) > Z){
            ret = mid;
            hi = mid - 1;
        }
        else lo = mid + 1;
    }
    cout << ret << '\n';
    return 0;
}