#include <bits/stdc++.h>
using namespace std;

int N, K, w, v, ret, dp[103][100003];
vector<int> W, V;
// dp[i][w] : 배낭 용량이 w일 때, 아이템 1, 2, ... i로 얻을 수 있는 최대 이득

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> K;  // N : 물품 개수, K 최대 용량
    W.push_back(0);  // 0번째 인덱스 채우기
    V.push_back(0);  // 0번째 인덱스 채우기
    for (int i = 1; i <= N; i++){
        cin >> w >> v;
        W.push_back(w);
        V.push_back(v);
    }
    for (int i = 1; i <= N; i++){
        for (int weight = 1; weight <= K; weight++){
            if (W[i] > weight) dp[i][weight] = dp[i - 1][weight];
            else dp[i][weight] = max(dp[i - 1][weight], V[i] +  dp[i - 1][weight - W[i]]); 
        }
    }
    cout << dp[N][K] << "\n";
    return 0;
}