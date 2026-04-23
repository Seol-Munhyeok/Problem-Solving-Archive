#include <bits/stdc++.h>
using namespace std;

/* 첫째 줄에 행렬의 개수 N(1 ≤ N ≤ 500)이 주어진다.
둘째 줄부터 N개 줄에는 행렬의 크기 r과 c가 주어진다. (1 ≤ r, c ≤ 500)
입력으로 주어진 행렬을 곱하는데 필요한 곱셈 연산의 최솟값을 출력한다. */

int N, r, c;
int m[502][502];  // dp[i][j] : A_i*A_i+1...A_j를 곱하는 최소 곱셈 횟수
int p[502][2];  // p[i][0] : i번째 행렬의 행 개수, p[i][1] : 열 개수

int main(){
    cin >> N;
    for (int i = 1; i <= N; i++){
        cin >> r >> c;
        p[i][0] = r;
        p[i][1] = c;
    }
    for (int r = 1; r <= N - 1; r++){
        for (int i = 1; i <= N - r; i++){
            int j = i + r;
            m[i][j] = m[i + 1][j] + p[i][0] * p[i][1] * p[j][1];
            for (int k = i + 1; k <= j - 1; k++){
                if (m[i][j] > m[i][k] + m[k + 1][j] + p[i][0] * p[k][1] * p[j][1])
                    m[i][j] = m[i][k] + m[k + 1][j] + p[i][0] * p[k][1] * p[j][1];
            }
        }
    }
    cout << m[1][N];
    return 0;
}
