#include <bits/stdc++.h>
using namespace std;
int N, a[102], cnt[102], ret;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    vector<pair<int, int>> v(N);
    for (int i = 0; i < N; i++){
        cin >> v[i].first >> v[i].second;
    }
    sort(v.begin(), v.end());
    for (int i = 0; i < N; i++){
        a[i] = v[i].second;
    }
    for (int i = 0; i < N; i++){
        int maxValue = 0;
        for (int j = 0; j < i; j++){
            if (a[j] < a[i] && maxValue < cnt[j]) maxValue = cnt[j];
        }
        cnt[i] = maxValue + 1;
        ret = max(ret, cnt[i]);
    }
    cout << N - ret << '\n';
    return 0;
}