#include <bits/stdc++.h>
using namespace std;

int N, L[2002], J[2002];

int knapsack(int i, int W){
    if (i <= 0 || W <= 0) return 0;
    if (L[i] >= W) return knapsack(i - 1, W);
    else {
        int not_included = knapsack(i - 1, W);
        int included = knapsack(i - 1, W - L[i]) + J[i];
        return max(not_included, included);
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 1; i <= N; i++) cin >> L[i];
    for (int i = 1; i <= N; i++) cin >> J[i];
    cout << knapsack(N, 100) << '\n';
    return 0;
}