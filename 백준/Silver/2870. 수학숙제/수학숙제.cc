#include <bits/stdc++.h>
using namespace std;

int N;
string s, ret;
vector<string> v;

bool cmp(string a, string b){
    if (a.size() < b.size()) 
        return true;
    else if (a.size() > b.size()) 
        return false;
    else return a < b;
}

string removeZero(string &str){
    int idx = str.find_first_not_of('0');
    if (idx != string::npos)
        return str.substr(idx);
    else return "0";
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    while (N--){
        cin >> s;
        ret = "";
        for (int i = 0; i < s.size(); i++){
            if (isalpha(s[i])){
                if (ret != "") v.push_back(removeZero(ret));
                ret = "";
            }
            if (isdigit(s[i])) ret += s[i];
        }
        if (ret != "") v.push_back(removeZero(ret));
    }
    sort(v.begin(), v.end(), cmp);
    for (string i : v) cout << i << '\n';
    return 0;
}