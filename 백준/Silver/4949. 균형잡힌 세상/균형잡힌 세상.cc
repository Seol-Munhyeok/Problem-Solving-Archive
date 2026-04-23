#include <bits/stdc++.h>
using namespace std;

const char open[] = {'(', '['};
const char close[] = {')', ']'};
string s;

bool check(string s){
    stack<char> stk;
    for (char ch : s){
        for (int i = 0; i <= 1; i++){
            if (ch == open[i]) stk.push(ch);
            else if (ch == close[i]){
                if (stk.size()  && stk.top() == open[i])
                    stk.pop();
                else return false;
            }
        }
    }
    return stk.empty();
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    while (true){
        getline(cin, s); if (s == ".") break;
        if (check(s)) cout << "yes\n";
        else cout << "no\n";
    }
    return 0;
}