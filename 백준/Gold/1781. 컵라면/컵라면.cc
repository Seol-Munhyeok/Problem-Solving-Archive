#include <bits/stdc++.h>
using namespace std;

int N, d, c, ret;
vector<pair<int, int>> v;
priority_queue<int, vector<int>, greater<int>> pq;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    vector<pair<int, int>> v;
    for (int i = 0; i < N; i++){
        cin >> d >> c;
        v.push_back({d, c});
    }
    sort(v.begin(), v.end());
    for (int i = 0; i < N; i++){
        pq.push(v[i].second);
        if (pq.size() > v[i].first) pq.pop();
    }
    while (pq.size()){
        ret += pq.top();
        pq.pop();
    }
    cout << ret << '\n';
    return 0;
}