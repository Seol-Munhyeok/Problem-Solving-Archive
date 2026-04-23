#include <bits/stdc++.h>
using namespace std;

int n, x, s, e, ret;
int a[1000002];
int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    cin >> x;
    sort(a, a + n);
    s = 0; e = n - 1;
    while (s < e){
        if (a[s] + a[e] > x) e--;
        else if (a[s] + a[e] < x) s++;
        else {
            ret++;
            s++; e--;
        }
    }
    cout << ret << '\n';
    return 0;
}