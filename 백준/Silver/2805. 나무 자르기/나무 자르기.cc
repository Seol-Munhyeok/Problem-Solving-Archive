#include <bits/stdc++.h>
using namespace std;
using ll = long long;
ll N, M, a[1000004], ret;

bool check(int mid){
    ll sum = 0;
    for (int i = 0; i < N; i++){
        if (a[i] - mid > 0) sum += a[i] - mid;
    }
    return sum >= M;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    ll lo = 1, hi = 0, mid;
    for (int i = 0; i < N; i++){
        cin >> a[i];
        hi = max(hi, a[i]);
    }
    while (lo <= hi){
        mid = (lo + hi) / 2;
        if (check(mid)){
            ret = max(ret, mid);
            lo = mid + 1;
        }
        else hi = mid - 1;
    }
    cout << ret << '\n';
    return 0;
}