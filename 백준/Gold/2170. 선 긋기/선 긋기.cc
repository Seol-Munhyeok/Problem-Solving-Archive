#include <bits/stdc++.h>
using namespace std;

int N, l, r, ret;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    vector<pair<int, int>> v(N);
    for (int i = 0; i < N; i++){
        cin >> v[i].first >> v[i].second;
    }
    sort(v.begin(), v.end());
    l = v[0].first; r = v[0].second;
    for (int i = 1; i < N; i++){
        if (r < v[i].first) {
            ret += (r - l);
            l = v[i].first;
            r = v[i].second;
        }
        else if (v[i].first <= r && v[i].second >= r){
            r = v[i].second;
        }
    }
    ret += (r - l);
    cout << ret << '\n';
    return 0;
}