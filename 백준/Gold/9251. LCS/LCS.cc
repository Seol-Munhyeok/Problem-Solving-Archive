#include <bits/stdc++.h>
using namespace std;

int lcs[1002][1002];
int m, n;
string A, B;
vector<char> res;

void printLCS(int a, int b){
    if (lcs[a][b] == 0) return;
    // 위쪽 먼저 탐색
    if (lcs[a][b] == lcs[a - 1][b]) a--;
    else if (lcs[a][b] == lcs[a][b - 1]) b--;
    else {
        res.push_back(A[a - 1]);
        a--; b--;
    }
    printLCS(a, b);
}

int main(){
    cin >> A >> B;
    m = A.size();  n = B.size();
    for (int i = 0; i <= m; i++) lcs[i][0] = 0;
    for (int j = 0; j <= n; j++) lcs[0][j] = 0;
    for (int i = 1; i <= m; i++){
        for (int j = 1; j <= n; j++){
            if (A[i - 1] == B[j - 1])  // 문자열 자체는 0번 인덱스
                lcs[i][j] = lcs[i - 1][j - 1] + 1;
            else lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1]); 
        }
    }
    cout << lcs[m][n] << "\n";
    //printLCS(m, n);
    //for (auto it = res.rbegin(); it != res.rend(); it++){
    //    cout << *it;
    //}
    //cout << "\n";
    return 0;
}