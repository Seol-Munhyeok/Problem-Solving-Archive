#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int a[20][20], ret = INF, n = 10;
map<int, int> mp;  // 색종이 개수 저장

bool check(int y, int x, int len){
    if (y + len > n || x + len > n) return false;
    for (int i = y; i < y + len; i++){
        for (int j = x; j < x + len; j++){
            if (a[i][j] == 0) return false;
        }
    }
    return true;
}

void draw(int y, int x, int len, int value){
    // value = 1 색종이 떼기
    // value = 0 색종이 붙이기
    for (int i = y; i < y + len; i++){
        for (int j = x; j < x + len; j++){
            a[i][j] = value;
        }
    }
}

void dfs(int y, int x, int cnt){
    if (cnt >= ret) return;
    if (x == n){
        dfs(y + 1, 0, cnt);
        return;
    }
    if (y == n){
        ret = min(ret, cnt);
        return;
    }
    if (a[y][x] == 0){
        dfs(y, x + 1, cnt);
        return;
    }
    for (int len = 5; len >= 1; len--){
        if (mp[len] == 5) continue;
        if (check(y, x, len)){
            mp[len]++;
            draw(y, x, len, 0);  // 붙이고
            dfs(y, x + len, cnt + 1);  // 그 다음거 체크
            draw(y, x, len, 1);  // 다시 떼고
            mp[len]--;
        }
    }
    return;
}
int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    for (int i = 0; i < 10; i++){
        for (int j = 0; j < 10; j++){
            cin >> a[i][j];
        }
    }
    dfs(0, 0, 0);
    cout << (ret == INF ? -1 : ret) << "\n";

    return 0;
}