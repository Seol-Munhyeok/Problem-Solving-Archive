#include <bits/stdc++.h>
using namespace std;

int n, sz, ret;
priority_queue<int, vector<int>, greater<int>> pq;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    while (n--){
        cin >> sz;
        pq.push(sz);
    }
    int a, b;
    while (pq.size() > 1){
        a = pq.top();  pq.pop();
        b = pq.top();  pq.pop();
        ret += a + b;
        pq.push(a + b);
    }
    cout << ret;
    return 0;
}