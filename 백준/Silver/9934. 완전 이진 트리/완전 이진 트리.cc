#include <bits/stdc++.h>
using namespace std;

int K, a[2000];
vector<int> ret[12];

void solve(int start, int end, int level){
    if (start > end) return;
    if (start == end){
        ret[level].push_back(a[start]);
        return;
    }
    int mid = (start + end) / 2;
    ret[level].push_back(a[mid]);
    solve(start, mid - 1, level + 1);
    solve(mid + 1, end, level + 1);
    return;
} 

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> K;
    int _end = (int)pow(2, K) - 1;
    for (int i = 0; i < _end; i++){
        cin >> a[i];
    }
    solve(0, _end, 1);
    for (int i = 1; i <= K; i++){
        for (int j : ret[i]) cout << j << ' ';
        cout << '\n';
    }
    return 0;
}
