#include <bits/stdc++.h>
using namespace std;

int N, M;
int a[10];
bool used[10];

void go(int k, int start){
    if (k == M){
        for (int i = 0; i < M; i++){
            cout << a[i] << " ";
        }
        cout << "\n";
        return;
    }
    for (int i = start; i <= N; i++){
        if (!used[i]){
            a[k] = i;
            used[i] = true;
            go(k + 1, i + 1);
            used[i] = false;
        }
    }
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    go(0, 1);
    return 0;
}