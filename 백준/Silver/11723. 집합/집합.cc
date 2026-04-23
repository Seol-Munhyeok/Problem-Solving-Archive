#include <bits/stdc++.h>
using namespace std;

int N, M, x;
string s;
int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> M;
    while (M--){
        cin >> s;
        if (s == "add"){
            cin >> x;
            N |= (1 << x);
        }
        else if (s == "remove"){
            cin >> x;
            N &= ~(1 << x);
        }
        else if (s == "check"){
            cin >> x;
            cout << (((N & (1 << x)) == 0) ? 0 : 1) << '\n';
        }
        else if (s == "toggle"){
            cin >> x;
            N ^= (1 << x);
        }
        else if (s == "all") N = (1 << 21) - 1;
        else if (s == "empty") N = 0;
    }
    return 0;
}