#include <bits/stdc++.h>
using namespace std;

stack<int> stk;
const int MAX = 1000004;
int N, a[MAX];

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++){
        cin >> a[i];
    }
    for (int i = 0; i < N; i++){
        int num = a[i];
        while (stk.size() && a[stk.top()] < num){
            a[stk.top()] = num;
            stk.pop();
        }
        stk.push(i);
    }
    while (stk.size()){
        a[stk.top()] = -1;
        stk.pop();
    }
    for (int i = 0; i < N; i++) cout << a[i] << ' ';

    return 0;
}