#include <bits/stdc++.h>
using namespace std;

int N, M, K, ret, A[11][11], nutrients[11][11];
// nutrients = 각 위치 현재 양분의 양, A = 추가로 공급될 양분의 양
vector<int> field[11][11];  // field에는 각 나무의 나이가 저장
const int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
const int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};

void springAndSummer(){
    // 어린 나무부터 양분을 나무 나이만큼 먹고, 나이가 1증가한다. 양분을 먹지못하면 즉시 죽는다.
    // 봄에 죽은 나무들이, 자기의 나이 / 2 만큼의 양분을 자기가 있던 칸에 양분으로 추가된다.
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            if (field[i][j].empty()) continue;
            vector<int> tmp;
            int diedTreeNuts = 0;
            sort(field[i][j].begin(), field[i][j].end());
            for (int age : field[i][j]){
                if (age <= nutrients[i][j]){
                    nutrients[i][j] -= age;
                    tmp.push_back(age + 1);
                }
                else diedTreeNuts += (age / 2);
            }
            nutrients[i][j] += diedTreeNuts;
            field[i][j] = tmp;
        }
    }
}

void reproduce(int y, int x){
    for (int i = 0; i < 8; i++){
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
        field[ny][nx].push_back(1);
    }
}

void fall(){
    // 나이가 5의 배수인 나무의 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            if (field[i][j].empty()) continue;
            for (int age : field[i][j]){
                if (age % 5 == 0) reproduce(i, j);
            }
        }
    }
}

void winter(){
    // 땅에 양분을 추가한다.
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            nutrients[i][j] += A[i][j];
        }
    }
}


int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M >> K;
    fill(&nutrients[0][0], &nutrients[0][0] + 11 * 11, 5);
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            cin >> A[i][j];
        }
    }
    int x, y, z;
    for (int i = 0; i < M; i++){
        cin >> x >> y >> z;
        field[x - 1][y - 1].push_back(z);
    }
    while (K--){
        springAndSummer();
        fall();
        winter();
    }
    for (int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
            ret += field[i][j].size();
        }
    }
    cout << ret << '\n';
    return 0;
}