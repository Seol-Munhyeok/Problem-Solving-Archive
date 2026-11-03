import java.util.*;
class Solution {
    
    static class State {
        int y, x, dir, cost;
        
        State(int y, int x, int dir, int cost) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    // 상 우 하 좌
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};
    
    public int solution(int[][] board) {
        int n = board.length;
        int INF = Integer.MAX_VALUE;
        int[][][] dist = new int[n][n][4];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                Arrays.fill(dist[y][x], INF);
            }
        }
        
        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.cost, b.cost)
        );
        
        // 처음 위치에서 갈 수 있는 위치 pq에 저장
        for (int dir = 0; dir < 4; dir++) {
            int y = dy[dir], x = dx[dir];
            if (y < 0 || y >= n || x < 0 || x >= n) {
                continue;   
            }
            
            if (board[y][x] == 0) {
                dist[y][x][dir] = 100;
                pq.offer(new State(y, x, dir, 100));
            }
        }
        
        // 다익스트라 수행
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            
            if (cur.cost > dist[cur.y][cur.x][cur.dir]) {
                continue;
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                    continue;
                }
                if (board[ny][nx] == 1) {
                    continue;
                }
                
                int curCost = dist[cur.y][cur.x][cur.dir];
                int nextCost = curCost + 100;
                if (cur.dir != dir) {
                    nextCost += 500;
                    
                }
                if (nextCost < dist[ny][nx][dir]) {
                    dist[ny][nx][dir] = nextCost;
                    pq.offer(new State(ny, nx, dir, nextCost));
                }  
            }
        }
        
        // 최단비용 찾기
        int answer = INF;
        for (int dir = 0; dir < 4; dir++) {
            answer = Math.min(answer, dist[n - 1][n - 1][dir]);
        }
        return answer;
    }
}