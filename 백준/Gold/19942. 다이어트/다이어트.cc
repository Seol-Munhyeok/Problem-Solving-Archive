#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int N, mp, mf, ms, mv;
int b, c, d, e, ret = INF, sum;
struct A{
    int mp, mf, ms, mv, cost;
} a[16];
map<int, vector<vector<int>>> ret_v;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    cin >> mp >> mf >> ms >> mv;
    for (int i = 0; i < N; i++){
        cin >> a[i].mp >> a[i].mf >> a[i].ms >> a[i].mv >> a[i].cost;
    }
    for (int i = 1; i < (1 << N); i++){
        b = c = d = e = sum = 0;
        vector<int> v;
        for (int j = 0; j < N; j++){
            if (i & (1 << j)){
                v.push_back(j + 1);
                b += a[j].mp;
                c += a[j].mf;
                d += a[j].ms;
                e += a[j].mv;
                sum += a[j].cost;
            }
        }
        if (b >= mp && c >= mf && d >= ms && e >= mv){
            if (ret >= sum){
                ret = sum;
                ret_v[ret].push_back(v);
            }
        }
    }
    if (ret == INF) {
        cout << -1 << '\n';
        return 0;
    } 
    sort(ret_v[ret].begin(), ret_v[ret].end());
    cout << ret << '\n';
    for (int i : ret_v[ret][0]) {
        cout << i << ' ';
    }
    return 0;
}