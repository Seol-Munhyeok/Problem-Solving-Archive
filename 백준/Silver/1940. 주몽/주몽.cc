#include <bits/stdc++.h>
using namespace std;

int N, M;
int a[10000004];
int main(){
    ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++)
        cin >> a[i];
    sort(a, a + N);
    int start = 0, end = N - 1, cnt = 0;
    while (start < end){
        int sum = a[start] + a[end];
        if (sum > M) end--;
        else if (sum < M) start++;
        else {
            cnt++; start++; end--;
        }
    }
    cout << cnt << '\n';
    return 0;
}