#include <bits/stdc++.h>
using namespace std;

string s, str;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> str;
    s = "";
    int len = 0;
    bool ok = 1;
    for (char ch : str){
        s += ch;
        if (s == "pi" || s == "ka" || s == "chu"){
            len = 0;
            s = "";
        }
        else {
            len++;
            if (len >= 3) { ok = 0; break; }
        }
    }
    if (len > 0) ok = 0;

    if (ok) cout << "YES" << '\n';
    else cout << "NO" << '\n';
    return 0;
}