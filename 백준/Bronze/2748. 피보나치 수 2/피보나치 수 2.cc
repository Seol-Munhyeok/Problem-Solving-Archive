#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll dp[91], N;
ll fibo(ll idx){
    if (idx == 0 || idx == 1) return idx;
    ll &ret = dp[idx];
    if (ret) return ret;
    return ret = fibo(idx - 1) + fibo(idx - 2); 
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    cout << fibo(N) << '\n';
    return 0;
}