#include <bits/stdc++.h>
using namespace std;

int N, a, b;
vector<pair<int, int>> v;
int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++){
        cin >> a >> b;
        v.push_back({a, b});
    }
    sort(v.begin(), v.end());
    int end = 0;
    for (int i = 0; i < N; i++){
        if (end > v[i].first) end += v[i].second;
        else end = v[i].first + v[i].second;
    }
    cout << end << '\n';
    return 0;
}