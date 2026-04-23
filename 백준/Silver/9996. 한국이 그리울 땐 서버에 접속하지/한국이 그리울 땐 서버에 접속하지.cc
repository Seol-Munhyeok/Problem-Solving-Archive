#include <bits/stdc++.h>
using namespace std;

string str, ptrn, prefix, suffix;
int n;

int main(){
    ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    cin >> n;
    cin >> ptrn;
    while (n--){
        cin >> str;
        bool same = false;
        int pos = ptrn.find('*');
        prefix = ptrn.substr(0, pos);
        suffix = ptrn.substr(pos + 1, string::npos);
        reverse(suffix.begin(), suffix.end());
        if (prefix == str.substr(0, prefix.size())){
            str.erase(0, prefix.size());
            reverse(str.begin(), str.end());
            if (suffix == str.substr(0, suffix.size())){
                cout << "DA" << '\n';
                same = true;
            }
        }
        if (!same) cout << "NE" << '\n';
    }
    return 0;
}