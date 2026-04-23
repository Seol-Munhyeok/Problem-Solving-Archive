#include <bits/stdc++.h>
using namespace std;

int L, C;
char ch[17];
vector<char> v;

bool isVowel(char ch){
    return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
}

void go(int idx, string ret, int mo, int ja){
    if (ret.size() == L){
        if (mo >= 1 && ja >= 2) cout << ret << "\n";
        return;
    }
    if (idx >= C) return;  // idx가 범위를 넘어가면 중단
    // 현재 문자 선택하는 경우
    if (isVowel(ch[idx])) go(idx + 1, ret + ch[idx], mo + 1, ja);
    else go(idx + 1, ret + ch[idx], mo, ja + 1);
    // 현재 문자 선택 안하는 경우
    go(idx + 1, ret, mo, ja);
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> L >> C;
    for (int i = 0; i < C; i++) cin >> ch[i];
    sort(ch, ch + C);
    go(0, "", 0, 0);
    return 0;
}