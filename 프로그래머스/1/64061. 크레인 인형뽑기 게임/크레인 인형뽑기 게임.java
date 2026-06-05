/**
같은 모양의 인형 두 개가 바구니에 연속해서 쌓이게 되면 두 인형은 바구니에서 사라짐
    인형이 있으면 인형은 반드시 집어짐
    바구니는 모든 인형이 들어갈 수 있을 만큼 충분히 크다
board = 30*30 (N x N)
    0=빈칸, 1~100 인형
Return 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수

여러 개의 Stack 구조를 활용
*/

import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int N = board.length;
        Deque<Integer>[] dqs = new ArrayDeque[N];
        for (int i = 0; i < N; i++) {
            dqs[i] = new ArrayDeque<>();
        }
        for (int c = 0; c < N; c++) {
            for (int r = N - 1; r >= 0; r--) {
                if (board[r][c] == 0) break;
                dqs[c].push(board[r][c]);
            }
        }
        
        Deque<Integer> bucket = new ArrayDeque<>();
        int answer = 0;
        for (int move : moves) {
            if (dqs[move - 1].isEmpty()) continue;
            int x = dqs[move - 1].pop();
            if (!bucket.isEmpty() && bucket.peek() == x) {
                answer += 2;
                bucket.pop();
            } else {
                bucket.push(x);
            }  
        }
               
        return answer;
    }
}