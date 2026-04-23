#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9, MAX_N = 16;
int N, dp[MAX_N][1 << MAX_N], dist[MAX_N][MAX_N];

int tsp(int here, int visited){
    // 기저사례 : 모든 지역 방문했으면 거리 리턴
    // 마지막 지역에서 0번으로 갈 수 없으면 INF 리턴
    if (visited == (1 << N) - 1){
        return dist[here][0] ? dist[here][0] : INF;
    }
    // 메모이제이션
    int &ret = dp[here][visited];
    if (ret != -1) return ret;
    // 로직
    ret = INF;
    for (int i = 0; i < N; i++){
        if (visited & (1 << i)) continue;  // 지역을 방문했으면 건너뜀
        if (dist[here][i] == 0) continue;  // 해당 지역을 방문할 수 없으면 건너뜀
        ret = min(ret, tsp(i, visited | (1 << i)) + dist[here][i]);
    }
    return ret;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            cin >> dist[i][j];
        }
    }
    memset(dp, -1, sizeof(dp));  // 초기화
    cout << tsp(0, 1) << '\n';
    return 0;
}