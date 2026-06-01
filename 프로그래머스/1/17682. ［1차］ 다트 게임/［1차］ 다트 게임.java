/**
 3번의 기회, 점수 0 ~ 10
  Single(S), Double(D), Triple(T) => 1제곱, 2제곱, 3제곱
  1. 3 세트로 문자열 분리
    숫자 10이 문제 -> 10을 'X'로 replace하고 숫자 또는 'X' 위치 기준으로 substring
  2. 점수 계산 규칙에 맞춰 계산
*/
class Solution {
    public int solution(String dartResult) {
        // 옵션 미적용 점수 계산
        dartResult = dartResult.replace("10", "X");
        String[] res = split(dartResult);
        int[] points = new int[3];
        for (int i = 0; i < 3; i++) {
            points[i] = getPoints(res[i]);
        }
        
        // 옵션 적용하여 최종 점수 계산
        for (int i = 0; i < 3; i++) {
            if (res[i].contains("#")) {
                points[i] = -points[i];
            } else if (res[i].contains("*")) {
                points[i] *= 2;
                if (i > 0) points[i - 1] *= 2;
            }
        }
        
        return points[0] + points[1] + points[2];
    }
    
    private int getPoints(String str) {       
        int p = str.charAt(0) == 'X' ? 10 : str.charAt(0) - '0';
        char b = str.charAt(1);
        
        if (b == 'S') return p;
        if (b == 'D') return p * p;
        return p * p * p;      
    }
    
    private String[] split(String str) {
        String[] res = new String[3];
        int s = 0, idx = 0;
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9' || ch == 'X') {
                res[idx++] = str.substring(s, i);
                s = i;
            }
        }
        res[idx++] = str.substring(s);
        return res;
    }
}