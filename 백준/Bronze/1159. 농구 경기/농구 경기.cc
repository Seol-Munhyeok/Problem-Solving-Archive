#include <bits/stdc++.h>
using namespace std;

map<char, int> mp;
string s;
int n;
int main(){
    ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    cin >> n;
    while (n--){
        cin >> s;
        mp[s[0]]++;
    }
    bool flag = false;
    for (auto it : mp){
        if (it.second >= 5) {
            cout << it.first;
            flag = true;
        }
    }
    if (!flag) cout << "PREDAJA";
    return 0;
}