#include <bits/stdc++.h>
using namespace std;
using ll = long long;

ll N, M, K, a, b, c;

ll sum(vector<ll> &tree, ll idx){
    ll ret = 0;
    while (idx > 0){
        ret += tree[idx];
        idx -= (idx & -idx);
    }
    return ret;
}

void update(vector<ll> &tree, ll idx, ll value){
    while (idx < tree.size()){
        tree[idx] += value;
        idx += (idx & -idx);
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M >> K;
    vector<ll> v(N + 1);
    vector<ll> tree(N + 1);
    for (int i = 1; i <= N; i++){
        cin >> v[i];
        update(tree, i, v[i]);
    }
    M += K;
    while (M--){
        cin >> a;
        if (a == 1){
            cin >> b >> c;
            ll diff = c - v[b];
            v[b] = c;
            update(tree, b, diff);
        }
        else {
            cin >> b >> c;
            cout << sum(tree, c) - sum(tree, b - 1) << '\n';
        }
    }
    return 0;
}