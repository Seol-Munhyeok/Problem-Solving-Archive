import java.util.*;

class Solution {
    
    int m, n;
    int[][] picture;
    boolean[][] visited;
    final int[] dy = {-1, 0, 1, 0};
    final int[] dx = {0, 1, 0, -1};
    
    public int[] solution(int m, int n, int[][] picture) {
        this.m = m;
        this.n = n;
        this.picture = picture;
        visited = new boolean[m][n];
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if (picture[y][x] != 0 && !visited[y][x]) {
                    numberOfArea++;
                    int size = bfs(y, x);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    private int bfs(int y, int x) {
        int count = 0;
        
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(y, x));
        visited[y][x] = true;
        count++;
        
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            
            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || ny >= m || nx < 0 || nx >= n) continue;
                if (visited[ny][nx]) continue;
                if (picture[cur.y][cur.x] != picture[ny][nx]) continue;
                
                q.offer(new Pos(ny, nx));
                visited[ny][nx] = true;
                count++;
            }
        }
        
        return count;
    }
    
    static class Pos {
        int y, x;
        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}