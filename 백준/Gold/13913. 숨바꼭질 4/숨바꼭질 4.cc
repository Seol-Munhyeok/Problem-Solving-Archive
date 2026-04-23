#include <bits/stdc++.h>
using namespace std;
#define prev xxxx
#define next xxx

const int MAX = 200004;
int visited[MAX], prev[MAX], N, K, ret, here, next;
vector<int> v;
queue<int> q;

int main(){
    cin >> N >> K;
    visited[N] = 1;
    q.push(N);
    while (q.size()){
        here = q.front();
        q.pop();
        if (here == K){
            ret = visited[K];
            break;
        }
        for (int next : {here - 1, here + 1, here * 2}){
            if (next >= MAX || next < 0 || visited[next]) continue;
            visited[next] = visited[here] + 1;
            prev[next] = here; // trace!
            q.push(next);
        }
    }
    for (int i = K; i != N; i = prev[i]){
        v.push_back(i);
    }
    v.push_back(N);
    cout << ret - 1 << '\n';
    reverse(v.begin(), v.end());
    for (int i : v) cout << i << ' ';
    return 0;
}