#include <bits/stdc++.h>
#define real ffff
using namespace std;

int N, a[1001], ret = 1, cnt[1001], idx;
int prev_list[1001];
vector<int> real;

void go(int idx){
    if (idx == -1) return;
    real.push_back(a[idx]);
    go(prev_list[idx]);
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++) cin >> a[i];
    fill(prev_list, prev_list + 1001, -1);
    fill(cnt, cnt + 1001, 1);
    for (int i = 0; i < N; i++){
        for (int j = 0; j < i; j++){
            if (a[j] < a[i] && cnt[i] < cnt[j] + 1){
                cnt[i] = cnt[j] + 1;
                prev_list[i] = j;
                if (ret < cnt[i]){
                    ret = cnt[i];
                    idx = i;
                }
            }
        }
    }
    cout << ret << '\n';
    go(idx);
    for (int i = real.size() - 1; i >= 0; i--) cout << real[i] << ' ';
    return 0;
}