#include <bits/stdc++.h>
using namespace std;

int n, sum, a, ret = -1005;
int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    // 양수는 무조건 더하는 게 이득
    // 음수일 때는 포함할지 말지를 정하는데 포함해서 음수가 되면 버리고 0부터 새로 시작
    for (int i = 0; i < n; i++){
        cin >> a;
        sum += a;
        ret = max(ret, sum);
        if (sum < 0) sum = 0;
    }
    cout << ret << '\n';
    return 0;
}