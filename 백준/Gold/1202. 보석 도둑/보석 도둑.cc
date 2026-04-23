#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll N, K, ret;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> K;
    vector<pair<ll, ll>> v(N);
    vector<ll> bag(K);
    for (int i = 0; i < N; i++){
        cin >> v[i].first >> v[i].second;
    }
    for (int i = 0; i < K; i++){
        cin >> bag[i];
    }
    sort(v.begin(), v.end());
    sort(bag.begin(), bag.end());
    priority_queue<ll> pq;

    int j = 0;
    for (int i = 0; i < K; i++){
        while (j < N && v[j].first <= bag[i]) pq.push(v[j++].second);
        if (pq.size()){
            ret += pq.top(); pq.pop();
        }
    }
    cout << ret << '\n';
    return 0;
}