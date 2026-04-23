#include <bits/stdc++.h>
using namespace std;

int N, K, L, X, row, col, a[102][102], direction, turningTime;

queue<pair<int, char>> turn;
queue<pair<int, int>> snake;
map<pair<int, int>, int> mp;
char C, turningDirection;
bool isFinished = false;

void handleMove(int dir, int y, int x){
    bool apple = false;
    int dy, dx;
    if (dir == 0) dy = 0, dx = 1;
    if (dir == 1) dy = 0, dx = -1;
    if (dir == 2) dy = -1, dx = 0;
    if (dir == 3) dy = 1, dx = 0;
    int ny = y + dy;
    int nx = x + dx;
    if (ny >= N || ny < 0 || nx >= N || nx < 0 || mp[{ny, nx}]){
        isFinished = true;
        return;
    }
    if (a[ny][nx]){
        apple = true;
        a[ny][nx] = 0;
    }
    snake.push({ny, nx});
    mp[{ny, nx}] = 1;
    if (!apple){
        mp[snake.front()] = 0;
        snake.pop();
    }
    return;
}

int handleTurn(int cur, char dir){
    if (cur == 0){
        if (dir == 'D') return 3;
        else return 2;
    }
    if (cur == 1){
        if (dir == 'D') return 2;
        else return 3;
    }
    if (cur == 2){
        if (dir == 'D') return 0;
        else return 1;
    }
    if (cur == 3){
        if (dir == 'D') return 1;
        else return 0;
    }
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> K;
    while (K--){
        cin >> row >> col;
        a[row - 1][col - 1] = 1;  // 1 : 사과
    }
    cin >> L;
    while (L--){
        cin >> X >> C;
        turn.push({X, C});
    }
    snake.push({0, 0});
    mp[{0, 0}] = 1;
    int time = 0;
    direction = 0;  // 0 : 우, 1 : 좌, 2 : 상, 3 : 하
    turningTime = turn.front().first;
    turningDirection = turn.front().second;
    while (true){
        if (isFinished){
            cout << time << "\n";
            break;
        }
        time++;
        int y = snake.back().first;
        int x = snake.back().second;
        handleMove(direction, y, x);
        if (time == turningTime){
            direction = handleTurn(direction, turningDirection);
            if (turn.size()){
                turn.pop();
                turningTime = turn.front().first;
                turningDirection = turn.front().second;
            }
        }
    }
    return 0;
}