#include <bits/stdc++.h>
using namespace std;


int T, N;
string str, arr, b;
deque<string> d;

deque<string> spilt(const string& input, string delimeter){
    deque<string> result;
    auto start = 0;
    auto end = input.find(delimeter);
    while (end != string::npos){
        result.push_back(input.substr(start, end - start));
        start = end + delimeter.size();
        end = input.find(delimeter, start);
    }
    result.push_back(input.substr(start));
    return result;
}

void print(deque<string>& d){
    string ret = "[";
    for (int i = 0; i < d.size(); i++){
        ret += d[i];
        if (i < d.size() - 1) ret += ",";
    }
    ret += "]";
    cout << ret << '\n';
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> T;
    while (T--){
        cin >> str >> N >> arr;
        if (N >= 1){
            arr = arr.substr(1, arr.size() - 2);
            d = spilt(arr, ",");
        }
        else d = {};
        bool ok = true; 
        bool filpped = false;
        for (char ch : str){
            if (ch == 'R') filpped = !filpped;
            if (ch == 'D'){
                if (d.size() && filpped) d.pop_back();
                else if (d.size() && !filpped) d.pop_front();
                else {
                    ok = false;
                    cout << "error\n"; 
                    break;
                }
            } 
        }
        if (filpped) reverse(d.begin(), d.end());
        if (ok) print(d);
    }
    return 0;
}