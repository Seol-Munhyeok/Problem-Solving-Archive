#include <bits/stdc++.h>
using namespace std;

int N, M, cnt, tmp;
map<int, int> mp;
int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++){
        cin >> tmp; mp[tmp]++;
    }
    for (int i = 0; i < M; i++){
        cin >> tmp; mp[tmp]++;
    }
    for (auto it : mp){
        if (it.second == 1) cnt++;
    }
    cout << cnt << '\n';
    return 0;
}