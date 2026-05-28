import java.util.*;
/**
Return: 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return
fees = 기본 시간(분),	기본 요금(원),	단위 시간(분),	단위 요금(원)
records = 시각(시:분),	차량 번호,	내역 (공백으로 구분된 문자열)
    records의 원소들은 시각을 기준으로 오름차순으로 정렬되어 주어집니다.
    
[요금 계산 규칙]
입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주
전체 시간을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산
    누적 주차 시간 <= 기본 시간, 기본 요금
    누적 주차 시간 > 기본 시간, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구
        기본 요금 + ceil((누적 주차 시간 - 기본 시간) / 단위 시간) * 단위 요금

[누적 주차 시간 계산]
map에 차량 번호 key, value를 List로 입차, 출차, 입차, 출차, ... 시간을 저장하고 한 번에 계산
    잘못된 입력이 주어지지 않으므로 IN, OUT은 중요하지 않음
*/

class Solution {
    
    int baseTime, baseFee, unitTime, unitFee;
    Map<String, List<Integer>> mp;
    
    public int[] solution(int[] fees, String[] records) {
        // 입력 파싱
        baseTime = fees[0];
        baseFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
        
        // Map에 저장, key 정렬을 위해 TreeMap 사용
        mp = new TreeMap<>();
        for (String record : records) {
            addMap(record);
        }
        
        // Map을 기반으로 각 차량의 주차 요금 계산
        int[] answer = new int[mp.size()];
        int idx = 0;
        for (List<Integer> value : mp.values()) {
            answer[idx++] = getFee(value);
        }
        return answer;
    }
    
    private int getFee(List<Integer> lst) {      
        // 마지막 출차 기록이 없는 경우 23:59 추가
        if (lst.size() % 2 == 1) {
            lst.add(strToInt("23:59"));
        }
        int totalTime = 0;
        for (int i = 1; i < lst.size(); i += 2) {
            totalTime += (lst.get(i) - lst.get(i - 1));
        }
        
        if (totalTime <= baseTime) return baseFee;
        return baseFee + (int) Math.ceil(((double) (totalTime - baseTime) / unitTime)) * unitFee;
    }
    
    private void addMap(String record) {
        String[] arr = record.split(" ");
        String time = arr[0];
        String no = arr[1];
        if (!mp.containsKey(no)) {
            mp.put(no, new ArrayList<>());
        }
        mp.get(no).add(strToInt(time));
    }
    
    private int strToInt(String str) {
        int h = Integer.parseInt(str.substring(0, 2));
        int m = Integer.parseInt(str.substring(3));
        return h * 60 + m;
    }
}