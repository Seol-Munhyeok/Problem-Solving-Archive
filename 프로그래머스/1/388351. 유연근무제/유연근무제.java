/**
출근 희망 시각 + 10분까지 어플로 출근
토요일, 일요일의 출근 시각은 영향 주지 않음
모든 시각은 시에 100을 곱하고 분을 더한 정수로 표현
Return 평일에 설정한 출근 희망 시간 + 10분까지 출근한 직원의 수
1은 월요일, 2는 화요일, 3은 수요일, 4는 목요일, 5는 금요일, 6은 토요일, 7은 일요일
    1빼서 0-based로 바꾸고 (startday + 1) % 7 하면서 5, 6 일 때는 무시
*/
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        for (int i = 0; i < schedules.length; i++) {
            int day = startday - 1;
            boolean ok = true;
            for (int j = 0; j < 7; j++) {
                if (day != 5 && day != 6
                    && timeToInt(schedules[i]) + 10 < timeToInt(timelogs[i][j])) {
                    ok = false;
                    break;
                }
                day = (day + 1) % 7;
            }
            if (ok) answer++;
        }
        return answer;
    }
    
    private int timeToInt(int t) {
        return (t / 100) * 60 + (t % 100);
    }
}