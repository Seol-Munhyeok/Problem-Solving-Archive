#include <bits/stdc++.h>
using namespace std;
int a[11];  // 굴린 주사위 눈;
int token[4];  // 각 말이 위치한 현재 인덱스 저장
int v[104];  // 게임판의 인덱스와 점수 대응
const int TOTAL_TURNS = 10, FINISH = 100;
vector<int> adj[54];  // 게임판의 모양을 인접리스트로 저장

int move(int here, int cnt){
    // here 인덱스에서 cnt칸 만큼 이동한 후의 인덱스를 반환
    if (here == FINISH) return FINISH;
    if (adj[here].size() >= 2){
        here = adj[here][1]; cnt--;  // 파란색(갈림길)이면 먼저 이동시킨다.
    }
    if (cnt){
        queue<int> q;
        q.push(here);
        int there;
        while (q.size()){
            int x = q.front(); q.pop();
            there = adj[x][0];
            q.push(there);
            if (there == FINISH) break;
            cnt--;
            if (cnt == 0) break;
        }
        return there;
    }
    else return here;
}

bool isToken(int token_idx, int idx){
    // token_idx 위치에 idx번째 말 이외의 다른 말이 있으면 true 반환. 
    if (token_idx == FINISH) return false;
    for (int i = 0; i < 4; i++){
        if (i == idx) continue;
        if (token[i] == token_idx) return true;
    }
    return false;
}

void add(int here, int there){
    adj[here].push_back(there);
}

void setMap(){
    for (int i = 0; i <= 19; i++) add(i, i + 1);
    add(5, 21); add(21, 22), add(22, 23); add(23, 24);
    add(15, 29); add(29, 30), add(30, 31); add(31, 24);
    add(10, 27); add(27, 28), add(28, 24);
    add(24, 25); add(25, 26), add(26, 20); add(20, FINISH);

    for (int i = 1; i <= 20; i++) v[i] = 2 * i;
	v[21] = 13; v[22] = 16; v[23] = 19;  v[24] = 25; 
	v[27] = 22; v[28] = 24; v[25] = 30; v[26] = 35; 
	v[29] = 28; v[30] = 27; v[31] = 26;
}

int go(int here){
    if (here == TOTAL_TURNS) return 0;
    int ret = 0;
    for (int i = 0; i < 4; i++){
        int cur_idx = token[i];
        int next_idx = move(cur_idx, a[here]);
        if (isToken(next_idx, i)) continue;  // 움직여서 그 위치에 다른 말 있으면 pass.
        token[i] = next_idx;  // 그 말을 움직이고
        // 그 다음 주사위 눈에 대해 반복하여 점수를 누적하여 ret와 비교한다.
        ret = max(ret, go(here + 1) + v[next_idx]);  
        token[i] = cur_idx;  // 다시 이동하기 전의 위치로 원상복구한다.
    }
    return ret;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    setMap();
    for (int i = 0; i < TOTAL_TURNS; i++) cin >> a[i];
    cout << go(0) << '\n';
    return 0;
}