#include <bits/stdc++.h>
using namespace std;

int N, cnt, a[22][22], ret = 1e9;
vector<int> v;

void go(){
    int x = 0, y = 0;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            if (i == j) continue;
            if (v[i] == 1 && v[j] == 1) x += a[i][j];
            else if (v[i] == 0 && v[j] == 0) y += a[i][j];
        }
    }
    ret = min(ret, abs(x - y));
}

void combi(int start){
    if (cnt == (N / 2)){
        go();
        return;
    }
    for (int i = start + 1; i < N; i++) {
        v[i] = 1; cnt++;
        combi(i);
        v[i] = 0; cnt--;
    }
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            cin >> a[i][j];
        }
    }
    for (int i = 0; i < N; i++) v.push_back(0);
    combi(-1);
    cout << ret << '\n';
    return 0;
}