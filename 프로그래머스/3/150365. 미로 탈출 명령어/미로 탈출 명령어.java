class Solution {
    
    char[] cmd = {'d', 'l', 'r', 'u'};
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (k < dist || (k - dist) % 2 == 1) {
            return "impossible";
        }
        
        StringBuilder answer = new StringBuilder();
        while (k > dist) {
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }
                k--;
                dist = Math.abs(nx - r) + Math.abs(ny - c);
                answer.append(cmd[dir]);
                x = nx;
                y = ny;
                break;                              
            }
        }
        
        int deltaX = x - r;
        int deltaY = y - c;
        // l -> u
        if (deltaX >= 0 && deltaY >= 0) {
            for (int i = 0; i < Math.abs(deltaY); i++) {
                answer.append('l');
            }
            for (int i = 0; i < Math.abs(deltaX); i++) {
                answer.append('u');
            }  
        }
        // r -> u
        else if (deltaX >= 0 && deltaY <= 0) {
            for (int i = 0; i < Math.abs(deltaY); i++) {
                answer.append('r');
            }
            for (int i = 0; i < Math.abs(deltaX); i++) {
                answer.append('u');
            }
            
        }
        // d -> l
        else if (deltaX <= 0 && deltaY >= 0) {
            for (int i = 0; i < Math.abs(deltaX); i++) {
                answer.append('d');
            }
            for (int i = 0; i < Math.abs(deltaY); i++) {
                answer.append('l');
            }
            
        }
        // d -> u
        else if (deltaX <= 0 && deltaY <= 0) {
            for (int i = 0; i < Math.abs(deltaX); i++) {
                answer.append('d');
            }
            for (int i = 0; i < Math.abs(deltaY); i++) {
                answer.append('r');
            }   
        }

        return answer.toString();
    }
}