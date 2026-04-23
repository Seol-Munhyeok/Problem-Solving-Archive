#include <bits/stdc++.h>
using namespace std;

// tip : '시각'은 항상 '이상'~'미만'이다. (출발시각, 끝난 시각)
// 틀린 이유 : 시각이 1 6이면 1분부터 5분까지(5분포함)만 시간을 썼다고 표기해야한다.
// 즉, 시작, 끝 시각이 주어지고, 시간을 고려해야하면 끝 시각은 포함하면 안된다!!
int ret[101];
int A, B, C, a, b, res;
int main(){
    ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    cin >> A >> B >> C;
    for (int i = 0; i < 3; i++){
        cin >> a >> b;
        for (int j = a; j < b; j++){
            ret[j]++;
        }
    }

    for (int i = 1; i <= 100; i++){
        if (ret[i] == 1) res += A;
        else if (ret[i] == 2) res += 2 * B;
        else if (ret[i] == 3) res += 3 * C;
    }

    cout << res << '\n';

    return 0;
}