#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
int d[105][105];
int n, m;

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n >> m;
    // 처음에 전부 거리 무한대로 초기화
    for (int i = 1; i <= n; i++){
        fill(d[i], d[i] + 1 + n, INF);
    }
    // 직접 거쳐가는 경로 거리 초기화
    while (m--){
        int a, b, c;
        cin >> a >> b >> c;
        d[a][b] = min(d[a][b], c);
    }
    // 자기 자신으로 가는 경우 0 초기화
    for (int i = 1; i <= n; i++) d[i][i] = 0;

    // Floyd-Warshall
    for (int k = 1; k <= n; k++){
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                // d[i][j] = min(d[i][j], d[i][k]+d[k][j]);
                // 시간 상 아래 코드가 더 빠름
                if (d[i][j] > d[i][k] + d[k][j]){
                    d[i][j] = d[i][k] + d[k][j];
                }
            }
        }
    }
    // 결과 출력
    for (int i = 1; i <= n; i++){
        for (int j = 1; j <= n; j++){
            if (d[i][j] == INF) cout << "0 ";
            else cout << d[i][j] << " ";
        }
        cout << "\n";
    }
    return 0;
}