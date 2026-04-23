#include <bits/stdc++.h>
using namespace std;

int T, N, M, tmp;

int check(int target, vector<int>& v){
    int l = 0, r = v.size() - 1;
    int mid;
    while (l <= r){
        mid = (l + r) / 2;
        if (v[mid] > target) r = mid - 1;
        else if (v[mid] == target) return 1;
        else l = mid + 1;
    }
    return 0;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> T;
    while (T--){
        cin >> N;
        vector<int> v;
        for (int i = 0; i < N; i++){
            cin >> tmp;
            v.push_back(tmp);
        }
        sort(v.begin(), v.end());
        cin >> M;
        for (int i = 0; i < M; i++){
            cin >> tmp;
            cout << check(tmp, v) << '\n';
        }
    }
    return 0;
}