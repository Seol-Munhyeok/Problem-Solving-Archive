#include <bits/stdc++.h>
using namespace std;

int N, s, e, ret = 1;
vector<pair<int, int>> v;

bool cmp(pair<int, int> a, pair<int, int> b){
    if (a.second == b.second) return a.first < b.first;
    return a.second < b.second;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++){
        cin >> s >> e;
        v.push_back({s, e});
    }
    sort(v.begin(), v.end(), cmp);
    int end = v.front().second;
    for (int i = 1; i < N; i++){
        if (v[i].first >= end || v[i].first == v[i].second){
            ret++;
            end = v[i].second;
        }
    }
    cout << ret << '\n';
    return 0;
}