#include <vector>
#include <bits/stdc++.h>

using namespace std;

int solution(vector<int> nums)
{
    int answer = 0;
    unordered_map<int, int> count;
    int N = nums.size();
    
    for (int num : nums) {
        count[num]++;
    }
    
    if (count.size() >= N / 2) {
        answer = N / 2;
    } else {
        answer = count.size();
    }
    
    return answer;
}