#include <bits/stdc++.h>
using namespace std;

int N, M, cnt, x;
vector<int> a, b;
bool check(int target){
    int l = 0, r = N - 1;
    while (l <= r){
        int mid = (l + r) / 2;
        if (a[mid] < target) l = mid + 1;
        else if (a[mid] > target) r = mid - 1;
        else return true;
    }
    return false;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    
    for (int i = 0; i < N; i++) {
        cin >> x;
        a.push_back(x);
    }
    for (int i = 0; i < M; i++) {
        cin >> x;
        b.push_back(x);
    }
    sort(a.begin(), a.end());
    for (int num : b) if (check(num)) cnt++;
    cout << N + M - (2 * cnt) << '\n';
    return 0;
}