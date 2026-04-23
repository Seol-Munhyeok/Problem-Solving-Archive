#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll N, ATK, CurHP, MaxHP, tmp, ret, t, h, a;
ll lst[130000][3];

bool check(ll mid){
    MaxHP = mid;
    CurHP = MaxHP;
    for (int i = 0; i < N; i++){
        t = lst[i][0], a = lst[i][1], h = lst[i][2];
        if (t == 1){
            if (h % ATK == 0) CurHP -= (((h / ATK) - 1) * a);
            else CurHP -= ((h / ATK) * a);
            if (CurHP <= 0) return false;
        }
        else {
            ATK += a;
            CurHP = min(MaxHP, CurHP + h);
        }
    }
    return true;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> ATK;
    for (int i = 0; i < N; i++){
        cin >> lst[i][0] >> lst[i][1] >> lst[i][2];
    }
    ll lo = 1, hi = 9e18;
    tmp = ATK;
    while (lo <= hi){
        ll mid = (lo + hi) / 2;
        ATK = tmp;
        if (check(mid)){
            hi = mid - 1;
            ret = mid;
        }
        else lo = mid + 1;
    }
    cout << ret << '\n';
    return 0;
}