#include <bits/stdc++.h>
using namespace std;

string s, bomb, ret;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> s >> bomb;
    int len = bomb.size();
    for (char ch : s){
        ret += ch;
        if (ret.size() >= len){
            if (ret.substr(ret.size() - len) == bomb){
                ret.erase(ret.size() - len, len);
            }
        }
    }
    cout << ((ret.size() == 0) ? "FRULA" : ret) << '\n';
    return 0;
}