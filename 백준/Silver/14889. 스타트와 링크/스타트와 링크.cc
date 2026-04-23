#include <bits/stdc++.h>
using namespace std;

int s[22][22], ret = 1e9, N;

int go(vector<int>& a, vector<int>& b){
    pair<int, int> ret;
    for (int i = 0; i < N / 2; i++){
        for (int j = 0; j < N / 2; j++){
            if (i == j) continue;
            ret.first += s[a[i]][a[j]];
            ret.second += s[b[i]][b[j]];
        }
    }
    return abs(ret.first - ret.second);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            cin >> s[i][j];
        }
    }
    for (int i = 0; i < (1 << N); i++){
        if (__builtin_popcount(i) != N / 2) continue;
        vector<int> start, link;
        for (int j = 0; j < N; j++){
            if (i & (1 << j)) start.push_back(j);
            else link.push_back(j);
        }
        ret = min(ret, go(start, link));
    }
    cout << ret << '\n';
    return 0;
}