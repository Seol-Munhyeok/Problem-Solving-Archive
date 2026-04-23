#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int N, a[44], ret = INF;
string s;

void go(int here){
    if (here == N + 1){
        int sum = 0;
        for (int i = 1; i <= (1 << (N - 1)); i *= 2){
            int cnt = 0;
            for (int j = 1; j <= N; j++) if (a[j] & i) cnt++;
            sum += min(cnt, N - cnt);
        }
        ret = min(ret, sum);
        return;
    }
    go(here + 1);
    a[here] = ~a[here];
    go(here + 1);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 1; i <= N; i++){
        cin >> s;
        int value = 1;
        for (int j = 0; j < s.size(); j++){
            if (s[j] == 'T') a[i] |= value;
            value *= 2;
        }
    }
    go(1);
    cout << ret << '\n';
    return 0;
}
