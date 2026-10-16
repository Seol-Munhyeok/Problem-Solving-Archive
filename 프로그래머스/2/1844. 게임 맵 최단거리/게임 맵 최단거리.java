import java.util.*;
class Solution {
    
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};
    
    public int solution(int[][] maps) {
        Queue<Pos> q = new ArrayDeque<>();
        int n = maps.length;
        int m = maps[0].length;
        int[][] visited = new int[n][m];
        q.offer(new Pos(0, 0));
        visited[0][0] = 1;
        
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (cur.y == n - 1 && cur.x == m - 1) {
                return visited[n - 1][m - 1];
            }
            
            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                if (visited[ny][nx] != 0) continue;
                if (maps[ny][nx] == 0) continue;
                
                q.offer(new Pos(ny, nx));
                visited[ny][nx] = visited[cur.y][cur.x] + 1;
            }
        }
        
        return -1;
    }
    
    static class Pos {
        int y, x;
        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}