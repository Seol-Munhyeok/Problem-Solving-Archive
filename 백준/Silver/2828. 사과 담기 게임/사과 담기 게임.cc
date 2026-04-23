#include <bits/stdc++.h>
using namespace std;

int N, M, J, loc, ret;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    int start = 1, end = M;
    cin >> J;
    while (J--){
        int dx = 0;
        cin >> loc;
        if (end < loc) {
            dx = loc - end; start += dx; end += dx;
        }
        else if (start > loc) {
            dx = start - loc; start -= dx; end -= dx;
        } 
        ret += dx; 
    }
    cout << ret << '\n';
    return 0;
}