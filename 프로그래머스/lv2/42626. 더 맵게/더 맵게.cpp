#include <bits/stdc++.h>

using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int, vector<int>, greater<int>> pq(scoville.begin(), scoville.end());
    int answer = 0;
    
    while (!pq.empty() && pq.top() < K) {
        if (pq.size() < 2) return -1;
        
        int a = pq.top(); pq.pop();
        int b = pq.top(); pq.pop();
        pq.push(a + b * 2);
        
        answer++;
    }
    
    return answer;
}