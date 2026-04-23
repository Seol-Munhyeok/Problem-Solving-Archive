#include <bits/stdc++.h>
using namespace std;
char a[20];
int k, check[10];
vector<string> ret;

bool good(char x, char y, char op){
    return (x < y && op == '<') || (x > y && op == '>');
}

void go(int idx, string num){
    if (idx == k + 1){
        ret.push_back(num);
        return;
    }
    for (int i = 0; i <= 9; i++){
        if (check[i]) continue;  // 사용한 숫자면 continue
        if (idx == 0 || good(num[idx - 1], i + '0', a[idx - 1])){
            check[i] = 1;
            go(idx + 1, num + to_string(i));
            check[i] = 0;
        }
    }
    return;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> k;
    for (int i = 0; i < k; i++){
        cin >> a[i];
    }
    go(0, "");
    sort(ret.begin(), ret.end());
    cout << ret[ret.size() - 1] << '\n' << ret[0] << '\n';
    return 0;
}