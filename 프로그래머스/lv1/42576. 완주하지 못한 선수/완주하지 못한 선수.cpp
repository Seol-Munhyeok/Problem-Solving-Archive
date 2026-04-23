#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    unordered_map<string, int> mp;
    
    for (auto& part : participant) {
        mp[part]++;
    }
    
    for (auto& comp : completion) {
        mp[comp]--;
    }
    
    for (auto& p : mp) {
        if (p.second > 0) {
            return p.first;
        }
    }
}