#include <bits/stdc++.h>
using namespace std;

/* 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
1 X가 3으로 나누어 떨어지면, 3으로 나눈다.
2 X가 2로 나누어 떨어지면, 2로 나눈다.
3 1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 
연산을 사용하는 횟수의 최솟값을 출력하시오.
과정도 출력하시오 */

int dp[1000005];
int pre[1000005];  // 경로 추적용 테이블
int n;

int main(){
    cin >> n;
    dp[1] = 0;
    for (int i = 2; i <= n; i++){
        dp[i] = dp[i - 1] + 1;
        pre[i] = i - 1;
        if (i % 2 == 0 && dp[i] > dp[i / 2] + 1){
            dp[i] = dp[i / 2] + 1;
            pre[i] = i / 2;
        }
        if (i % 3 == 0 && dp[i] > dp[i / 3] + 1){
            dp[i] = dp[i / 3] + 1;
            pre[i] = i / 3;
        }
    }
    cout << dp[n] << "\n";

    int cur = n;
    while (true){
        cout << cur << " ";
        if (cur == 1) break;
        cur = pre[cur];
    }
    return 0;
}