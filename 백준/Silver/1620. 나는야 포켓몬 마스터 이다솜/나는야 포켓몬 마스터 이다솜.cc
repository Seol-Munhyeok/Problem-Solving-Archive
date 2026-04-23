#include <bits/stdc++.h>
using namespace std;

map<string, int> mp;
string a[100004];
string name, input;
int N, M;

int main(){
    ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    int i = 1;
    while (N--){
        cin >> name;
        mp[name] = i; 
        a[i] = name;
        i++;
    }
    while (M--){
        cin >> input;
        int key = atoi(input.c_str());
        if (key) cout << a[key] << '\n'; 
        else cout << mp[input] << '\n';
    }
    return 0;
}