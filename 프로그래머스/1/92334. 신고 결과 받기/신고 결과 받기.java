import java.util.*;
/**
Return : 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return
각 유저는 한 번에 한 명의 유저를 신고 가능
    신고 횟수 제한 없음
    한 유저를 여러 번 신고해도 1회로 처리
k번 이상 신고된 유저는 게시판 이용이 정지
    '해당 유저를 신고한' 모든 유저에게 정지 사실을 메일로 발송
    모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지시키며 메일 발송
    
[params]
id_list : 이용자의 ID, 여기에 담긴 순서대로 각 유저가 받은 메일 수 반환 <= 1000
report : 공백으로 구분, A -> B (A가 B를 신고하였음) <= 200_000

[풀이]
두 개의 Map 관리
    1. <String, Integer> ID : 신고 받은 횟수
    2. <String, Set<String>> ID : 나를 신고한 사람의 ID (중복 제거)
*/

class Solution {
    
    Map<String, Integer> reportCount;
    Map<String, Set<String>> snitches;
    Map<String, Integer> mailCount;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        reportCount = new HashMap<>();
        snitches = new HashMap<>();
        mailCount = new HashMap<>();
        
        // 신고 내역 저장
        for (String cmd : report) {
            addMap(cmd);
        }
        
        // k회 이상 신고받은 유저를 신고한 사람의 count 증가
        for (Map.Entry<String, Integer> entry : reportCount.entrySet()) {
            String reportedID = entry.getKey();
            int count = entry.getValue();
            
            if (count >= k) {
                for(String snitch : snitches.get(reportedID)) {
                    mailCount.put(snitch, mailCount.getOrDefault(snitch, 0) + 1);
                }
            }
        }
        
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
           answer[i] = mailCount.get(id_list[i]) == null ? 0 : mailCount.get(id_list[i]); 
        }
        return answer;
    }
    
    private void addMap(String cmd) {
        String[] arr = cmd.split(" ");  
        
        // 이미 같은 사람은 신고하지 않은 경우에만 count 추가
        if (snitches.get(arr[1]) == null || !snitches.get(arr[1]).contains(arr[0])) {
            reportCount.put(arr[1], reportCount.getOrDefault(arr[1], 0) + 1);
        }
        
        // 신고한 사람 저장
        if (!snitches.containsKey(arr[1])) {
            snitches.put(arr[1], new HashSet<>());
        }
        snitches.get(arr[1]).add(arr[0]);        
    }
}