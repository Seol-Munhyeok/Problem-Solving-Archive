#include <bits/stdc++.h>
using namespace std;

int N, M, J, loc, ret;
int a[14];

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    int start = 1, end = M;
    cin >> J;
    while (J--){
        int dx = 0;
        cin >> loc;
        // 바구니 오른쪽에 떨어질 때
        if (end < loc) {
            dx = loc - end; start += dx; end += dx;
        }
        // 바구니 왼쪽에 떨어질 때
        else if (start > loc) {
            dx = start - loc; start -= dx; end -= dx;
        } 
        ret += dx; 
    }
    cout << ret << '\n';
    return 0;
}