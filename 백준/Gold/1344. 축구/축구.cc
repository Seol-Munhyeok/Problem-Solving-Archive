#include <bits/stdc++.h>
using namespace std;

const int N = 19;
vector<int> range = {0,1,4,6,8,9,10,12,14,15,16,18};
int C[N];

void initBC(){
    // n = N - 1일 때 nCr 구하기
    int n = N;
    C[0] = C[1] = 1;
    for (int i = 2; i < N; i++){
        C[0] = C[i] = 1;
        for(int j = i - 1; j > 0; j--){
            C[j] += C[j - 1];
        } 
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    initBC();
    double a, b;
    cin >> a >> b;
    a /= 100;  b /= 100;
    double Sa = 0, Sb = 0;
    for (int r : range){
        Sa += C[r] * pow(a, r) * pow(1 - a, N - 1 - r);
        Sb += C[r] * pow(b, r) * pow(1 - b, N - 1 - r);
    }
    cout << 1 - Sa * Sb << "\n";
    return 0;
}