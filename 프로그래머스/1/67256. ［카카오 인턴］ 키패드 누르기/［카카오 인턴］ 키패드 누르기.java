/**
Return 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열
1,4,7 -> L
3,6,9 -> R
2,5,8,0 -> 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락
    두 엄지손가락의 거리가 같다면 hand에 주어진 손가락으로

키패드를 4*3 배열로 바라보고 각 번호의 좌표를 저장
매 번호를 누를 때마다 왼손과 오른손가락의 좌표를 변화
*/

import java.util.*;

class Solution {
    
    Pos[] pos;
    Pos left, right;
    StringBuilder sb;
    
    public String solution(int[] numbers, String hand) {
        // 키 패드 좌표 저장
        pos = new Pos[10];
        pos[0] = new Pos(3, 1);
        int num = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pos[num++] = new Pos(i, j);
            }
        }
        
        left = new Pos(3, 0);
        right = new Pos(3, 2);
        sb = new StringBuilder();
        for (int number : numbers) {
            processNumber(number, hand);
        }

        return sb.toString();
    }
    
    private void processNumber(int num, String hand) {
        if (num == 1 || num == 4 || num == 7) {
            left = pos[num];
            sb.append("L");
        } else if (num == 3 || num == 6 || num == 9) {
            right = pos[num];
            sb.append("R");
        } else {
            int leftDistance = getDistance(left, pos[num]);
            int rightDistance = getDistance(right, pos[num]);
            if (leftDistance < rightDistance) {
                left = pos[num];
                sb.append("L");
            } else if (leftDistance > rightDistance) {
                right = pos[num];
                sb.append("R");
            } else {
               if (hand.equals("left")) {
                   left = pos[num];
                   sb.append("L");
               } else {
                   right = pos[num];
                   sb.append("R");
               }
            }
        }
    }
    
    private int getDistance(Pos p1, Pos p2) {
        return Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
    }
    
    static class Pos {
        int y, x;
        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}