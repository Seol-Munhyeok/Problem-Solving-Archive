#include <bits/stdc++.h>
using namespace std;

int k;
vector<int> v;

void combi(int start, vector<int>& a){
    if (v.size() == 6){
        for (int i : v) cout << i << " ";
        cout << "\n";
        return;
    }
    for (int i = start + 1; i < k; i++){
        v.push_back(a[i]);
        combi(i, a);
        v.pop_back();
    }
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    while (true){
        cin >> k;
        if (k == 0) break;
        vector<int> a(k);
        for (int i = 0; i < k; i++){
            cin >> a[i];
        }
        v.empty();
        combi(-1, a);
        cout << "\n";
    }
    return 0;
}