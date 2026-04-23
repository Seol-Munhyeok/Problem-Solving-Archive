#include <bits/stdc++.h>
using namespace std;

const int MAX = 1501;
const int dy[] = {-1, 0, 1, 0};
const int dx[] = {0, 1, 0, -1};
int visited_swan[MAX][MAX], visited[MAX][MAX], R, C, day, swanY, swanX, y, x;
char a[MAX][MAX];
queue<pair<int, int>> waterQ, water_tempQ, swanQ, swan_tempQ;

void Qclear(queue<pair<int, int>> &q){
    queue<pair<int, int>> empty;
    swap(q, empty);
}

void water_melting(){
    while (waterQ.size()){
        tie(y, x) = waterQ.front();
        waterQ.pop();
        for (int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx]) continue;
            if (a[ny][nx] == 'X'){
                visited[ny][nx] = 1;
                water_tempQ.push({ny, nx});
                a[ny][nx] = '.';
            }
        }
    }
}

bool move_swan(){
    while (swanQ.size()){
        tie(y, x) = swanQ.front();
        swanQ.pop();
        for (int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited_swan[ny][nx]) continue;
            visited_swan[ny][nx] = 1;
            if (a[ny][nx] == '.') swanQ.push({ny, nx});
            else if (a[ny][nx] == 'X') swan_tempQ.push({ny, nx});
            else if (a[ny][nx] == 'L') return true;
        }
    }
    return false;
}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> R >> C;
    for (int i = 0; i < R; i++){
        for (int j = 0; j < C; j++){
            cin >> a[i][j];
            if (a[i][j] == 'L') {swanY = i, swanX = j;}
            if (a[i][j] == '.' || a[i][j] == 'L') {
                visited[i][j] = 1;
                waterQ.push({i, j});
            }
        }
    }
    swanQ.push({swanY, swanX});
    visited_swan[swanY][swanX] = 1;
    while (true){
        if (move_swan()) break;
        water_melting();
        waterQ = water_tempQ;
        swanQ = swan_tempQ;
        Qclear(water_tempQ);
        Qclear(swan_tempQ);
        day++;
    }
    cout << day << '\n';
    return 0;
}