#include <bits/stdc++.h>
using namespace std;

int N, M;
int a[10];
bool used[10];
vector<int> v;

void go(int k, int start){
    if (v.size() == M){
        for (int i : v) cout << i << " ";
        cout << "\n";
        return;
    }
    for (int i = start; i < N; i++){
        if (!used[i]){
            v.push_back(a[i]);
            used[i] = true;
            go(i + 1, i + 1);
            v.pop_back();
            used[i] = false;
        }
    }
}
int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++) cin >> a[i];
    sort(a, a + N);
    go(0, 0);
    return 0;
}