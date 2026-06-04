/**
 Return 꺼내려는 상자 번호가 주어졌을 때, 꺼내려는 상자를 포함해 꺼내야 하는 상자의 개수
 공식으로도 나올 거 같지만, 실제로 배열에 수를 채워넣고 탐색해도 충분하다
*/

class Solution {
    public int solution(int n, int w, int num) {
        // 배열에 상자 채우기
        int h = (n + w - 1) / w;
        int[][] arr = new int[h][w];
        boolean right = false;
        boolean finished = false;
        int boxNum = 1;
        for (int i = h - 1; i >= 0; i--) {
            right = !right;
            if (right) {
                for (int j = 0; j < w; j++) {
                    arr[i][j] = boxNum++;
                    if (boxNum > n) {
                        finished = true;
                        break;
                    }
                }
            } else {
                for (int j = w - 1; j >= 0; j--) {
                    arr[i][j] = boxNum++;
                    if (boxNum > n) {
                        finished = true;
                        break;
                    }
                }
            }          
            if (finished) break;            
        }
        
        // 꺼내야하는 상자 개수 구하기
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (arr[i][j] == num) {
                    return (arr[0][j] != 0) ? i + 1 : i;
                }
            }
        }
        
        return -1;
    }
}