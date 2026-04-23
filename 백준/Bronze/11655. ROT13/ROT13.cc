#include <bits/stdc++.h>
using namespace std;

string s;
int main(){
    getline(cin, s);
    for (char ch : s){
        if (isspace(ch) || isdigit(ch)) cout << ch;
        else if (isupper(ch)) printf("%c", 'A' + (ch -'A' + 13) % 26);
        else printf("%c", 'a' + (ch -'a' + 13) % 26);
    }
    return 0;
}