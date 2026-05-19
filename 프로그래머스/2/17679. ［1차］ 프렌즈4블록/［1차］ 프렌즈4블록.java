import java.util.*;
/**
2*2 격자 상에 같은 블록이 있으면 해당 블록은 사라짐
2*2가 겹친 채로 있어도 모두 사라짐
사라진 블록은 중력 적용 -> 이후 같은 2*2가 있으면 사라짐
n(폭), m(높이) <= 30
Return : 지워지는 블록의 개수

[1] 지울 2*2 블록 확인
2*2 단위로 모든 격자를 스캔하면서 같은 블록이 있으면 좌표만 저장함 (중복 있을 수 있으니 Set에 저장)
Set의 원소를 확인하면서 삭제 처리 (* 으로 처리)

[2] 중력 적용
열마다 따로 따로 중력 적용
    새로운 배열을 하나 더 만들고 순회하면서 빈칸이 아닌 칸을 순서대로 채운다
    그 후 해당 배열을 복사
    
[1], [2]를 [1]에서 아무 것도 나오지 않을 때 까지 반복
*/
class Solution {
    
    int m, n, answer;
    char[][] board;
    
    public int solution(int m, int n, String[] board) {
        this.m = m;  // 높이
        this.n = n;  // 가로
        this.board = new char[m][n];
        for (int i = 0; i < m; i++) {
            this.board[i] = board[i].toCharArray();
        }
        answer = 0;
        
        while (true) {
            boolean flag = checkAndRemoveBlock();
            if (!flag) break;
            moveDownBoard();
        }
                
        return answer;
    }
    
    // 지워야할 블록을 찾고 board에서 제거 (지워야할 블록이 없으면 false 반환)
    private boolean checkAndRemoveBlock() {
        Set<Integer> removedPos = new HashSet<>();
        
        for (int i = 0; i <= m - 2; i++) {
            for (int j = 0; j <= n - 2; j++) {
                if (board[i][j] != '*' && same(i, j)) {
                    removedPos.add(i * n + j);
                    removedPos.add((i + 1) * n + j);
                    removedPos.add(i * n + (j + 1));
                    removedPos.add((i + 1) * n + (j + 1));                   
                }
            }
        }
        
        if (removedPos.isEmpty()) return false;
        
        for (int pos : removedPos) {
            board[pos / n][pos % n] = '*';
            answer++;
        }        
        return true;   
    }
    
    private boolean same(int y, int x) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (board[y][x] != board[y + i][x + j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void moveDownBoard() {
        for (int c = 0; c < n; c++) {
            char[] temp = new char[m];
            for (int i = 0; i < m; i++) temp[i] = '*';
            int idx = 0;

            for (int r = m - 1; r >= 0; r--) {
                if (board[r][c] != '*') {
                    temp[idx++] = board[r][c];
                }
            }
            
            idx = 0;
            for (int r = m - 1; r >= 0; r--) {
                board[r][c] = temp[idx++];                
            }
        }
    }                                                                   
}