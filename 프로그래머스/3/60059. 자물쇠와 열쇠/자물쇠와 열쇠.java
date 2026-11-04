import java.util.*;
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int n = key.length;
        for (int i = 0; i < 4; i++) {
            key = rotate90(key);
            if (possible(key, lock)) {
                return true;
            }
        }
        
        return false;
    }
    
    private int[][] rotate90(int[][] arr) {
        int n = arr.length;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - 1 - i] = arr[i][j];
            }
        }
        return temp;
    }
    
    private boolean possible(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;
        
        for (int dy = -(m - 1); dy < n; dy++) {
            for (int dx = -(m - 1); dx < n; dx++) {
                boolean[][] placedKey = new boolean[n][n]; 
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        if (i + dy < 0 || i + dy >= n || j + dx < 0 || j + dx >= n) continue;
                        placedKey[i + dy][j + dx] = (key[i][j] == 1);
                    }
                }
                
                boolean ok = true;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (lock[i][j] + (placedKey[i][j] ? 1 : 0) != 1) {
                            ok = false;
                            break;
                        }
                    }
                }
                
                if (ok) return true;
            }
        }
        return false;
    }
}