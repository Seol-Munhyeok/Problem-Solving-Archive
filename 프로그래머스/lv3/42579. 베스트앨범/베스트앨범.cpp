#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    unordered_map<string, int> count;
    unordered_map<string, vector<int>> index;
    
    for (int i = 0; i < genres.size(); i++) {
        count[genres[i]] += plays[i];
        index[genres[i]].push_back(i);
    }
    
    vector<pair<string, int>> v(count.begin(), count.end());
    
    sort(v.begin(), v.end(), 
        [](const auto& a, const auto& b) {
            return a.second > b.second;
        });
    
    for (auto& [genre, _] : v) {
        auto& song_indices = index[genre];
        
        sort(song_indices.begin(), song_indices.end(), 
            [&](int a, int b) {
                if (plays[a] == plays[b]) return a < b;
                return plays[a] > plays[b];
            }
        );
        
        for (int i = 0; i < min(2, (int)song_indices.size()); i++) {
            answer.push_back(song_indices[i]);
        }
    }
    
    return answer;
}