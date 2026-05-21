import java.util.*;
/**
셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착
하나의 셔틀에는 최대 m명의 승객
n = 2, t = 10, m = 2
["09:10", "09:09", "08:00"]
셔틀 오는 간격
    09:00, 09:10
09:00에 08:00에 온 사람 셔틀 타고 감
09:10에 09:09에 온 사람이 타고, 내가 타기위해서는 최대 09:09에 도착하면 됨

Return 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각
    같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다
    모든 크루는 23:59에 집에 돌아간다
    
[풀이]
문제 조건에 따라 버스에 태우는 것 시뮬레이션
    사람 다 태우고 버스에 자리가 남으면 그 '버스가 오는 시각'에 맞춰서 오면 됨
    사람 다 태우고 버스에 자리가 없으면 '마지막 승객이 오는 시각'에서 1분만 더 일찍 오면 됨

[구현]
HH:MM -> 정수로 변환, pq에 담음
09:00 = 540 부터 시작 (now)
while (버스가 n대까지 도착 or !pq.isEmpty()):
    while (pq.peek() <= now and m명 까지):
        pq.poll()
    now += t
if (pq.isEmpty()):
    answer = now - 1
else:
    answer = now
*/
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (String s : timetable) {
            pq.offer(strToInt(s));
        }
        
        int now = strToInt("09:00");
        int arrivedBusCnt = 0;
        int crewCnt = 0;
        int last = 0;
        while (arrivedBusCnt < n) {
            arrivedBusCnt++;
            crewCnt = 0;
            while (!pq.isEmpty() && pq.peek() <= now && crewCnt < m) {
                crewCnt++;
                last = pq.poll();
            }
            if (arrivedBusCnt < n) now += t;
        }
        
        System.out.println(crewCnt);
        if (crewCnt == m) return intToStr(last - 1);
        else return intToStr(now);
    }
    
    private int strToInt(String str) {
        int h = Integer.parseInt(str.substring(0, 2));
        int m = Integer.parseInt(str.substring(3));
        return h * 60 + m;
    }
    
    private String intToStr(int t) {
        return String.format("%02d:%02d", t / 60, t % 60);
    } 
}