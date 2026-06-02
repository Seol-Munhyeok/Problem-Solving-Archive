import java.util.*;
/**
(2, 2, 5, 4) 회전일 때 회전에 영향을 받는 행을 List에 담기
    lst = [(2, 2), (2, 3), (2, 4), (3, 4), (4, 4), (5, 4), (5, 3), (5, 2), (4, 2), (3, 2)]
lst[i] = lst[i - 1] 방식으로 뒤에 있는 요소를 덮어쓰기
   역순으로 채워넣어야 함
*/
class Solution {
    
    int[][] arr;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        arr = initArr(rows, columns);
        int[] answer = new int[queries.length];
        int idx = 0;
        for (int[] query : queries) {
            answer[idx++] = rotate(query);
        } 
        return answer;
    }
    
    private int rotate(int[] q) {
        List<Pos> lst = getRotateOrder(q);
        
        int minValue = 1_000_000_000;
        for (Pos p : lst) {
            minValue = Math.min(minValue, arr[p.r][p.c]);
        }
        
        int tmpValue = arr[lst.get(lst.size() - 1).r][lst.get(lst.size() - 1).c];
        for (int i = lst.size() - 1; i >= 1; i--) {  
            arr[lst.get(i).r][lst.get(i).c] = arr[lst.get(i - 1).r][lst.get(i - 1).c];
        }
        arr[lst.get(0).r][lst.get(0).c] = tmpValue;
        
        return minValue;
    }
    
    private List<Pos> getRotateOrder(int[] q) {
        List<Pos> lst = new ArrayList<>();
        int r1 = q[0] - 1, c1 = q[1] - 1, r2 = q[2] - 1, c2 = q[3] - 1;
        
        for (int c = c1; c < c2; c++) {
            lst.add(new Pos(r1, c));
        }
        for (int r = r1; r < r2; r++) {
            lst.add(new Pos(r, c2));
        }
        for (int c = c2; c > c1; c--) {
            lst.add(new Pos(r2, c));
        }
        for (int r = r2; r > r1; r--) {
            lst.add(new Pos(r, c1));
        }
        return lst;
    }
    
    private int[][] initArr(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                arr[r - 1][c - 1] = (r - 1) * columns + c;
            }
        }
        return arr;
    }
    
    static class Pos {
        int r, c;
        Pos (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}