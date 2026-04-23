#include <bits/stdc++.h>
using namespace std;

int N;
void honoi(int n, int a, int b){
    // 원판 n개를 기둥 a에서 b로 옮기는 방법을 출력하는 함수
    // 각 기둥이 1, 2, 3이므로 a도 b도 아닌 기둥은 (6 - a - b)임
    if (n == 1){
        cout << a << " " << b << "\n";
        return;
    }
    honoi(n - 1, a, 6 - a - b);
    cout << a << " " << b << "\n";
    honoi(n - 1, 6 - a - b, b);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N;
    cout << (int)pow(2, N) - 1 << "\n";
    honoi(N, 1, 3);
    return 0;
}