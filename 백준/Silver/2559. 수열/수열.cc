#include <bits/stdc++.h>
using namespace std;

const int MAX = 100004;
int N, K, ret;
int a[MAX], psum[MAX];

int main(){
    ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    cin >> N >> K;
    for (int i = 1; i <= N; i++) cin >> a[i];
    for (int i = 1; i <= N; i++)
        psum[i] = psum[i - 1] + a[i];
    int max = -1e9;
    for (int i = 1; i <= N - K + 1; i++){
        ret = psum[i + K - 1] - psum[i - 1];
        if (ret > max) max = ret;
    }
    cout << max << '\n';

    return 0;
}