#include <bits/stdc++.h>
using namespace std;

int N;
bool isPrime(int n){
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    for (int i = 5; i * i <= n; i++){
        if (n % i == 0) return false;
    }
    return true;
}

void go(int digit, int cur){
    if (digit == N){
        cout << cur << "\n";
        return;
    }
    for (int nxt = 0; nxt <= 9; nxt++){
        int tmp = 10 * cur + nxt;
        if (isPrime(tmp)) go(digit + 1, tmp);
    }
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    go(1, 2);
    go(1, 3);
    go(1, 5);
    go(1, 7);
    return 0;
}