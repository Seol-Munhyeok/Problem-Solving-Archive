#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

int solution(vector<vector<int>> routes) {
    sort(routes.begin(), routes.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[1] < b[1];
    });
    
    int cam = -30003;
    int placed = 0;
    int answer = 0;
    for (auto& r : routes) {
        int s = r[0], e = r[1];
        if (!placed || s > cam) {
            cam = e;
            answer++;
            placed = 1;
        }
    }
    
    return answer;
}