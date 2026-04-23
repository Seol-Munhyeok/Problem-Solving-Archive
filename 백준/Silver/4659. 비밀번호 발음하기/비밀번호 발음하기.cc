#include <bits/stdc++.h>
using namespace std;

string s;
char vowels[] = {'a', 'e', 'i', 'o', 'u'};

bool is_vowel(char ch){
    for (int i = 0; i < 5; i++)
        if (ch == vowels[i]) return true;
    return false;
}

bool check1(string str){
    // 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
    for (int i = 0; i < str.size(); i++) 
        if (is_vowel(str[i])) return true;
    return false;
}

bool check2(string str){
    // 모음(v)이 3개 혹은 자음(c)이 3개 연속으로 오면 안 된다.
    int v = 0, c = 0;
    for (char ch : str){
        if (v == 3 || c == 3) break;
        if (is_vowel(ch)) { c = 0; v++; }
        else { v = 0; c++; }
    }
    if (v == 3 || c == 3) return false;
    else return true;
}

bool check3(string str){
    // 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
    if (str.size() == 1) return true;
    for (int i = 1; i < str.size(); i++){
        if (str[i] != 'e' && str[i] != 'o' && str[i] == str[i - 1])
            return false;
    }
    return true;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    while (cin >> s){
        if (s == "end") break;
        if (check1(s) && check2(s) && check3(s))
            cout << "<" << s << "> is acceptable." << '\n';
        else cout << "<" << s << "> is not acceptable." << '\n';
    }
    return 0;
}