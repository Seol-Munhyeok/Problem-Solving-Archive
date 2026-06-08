
import java.util.*;

class Solution {
    
    Map<String, Integer> dict;
    
    public int[] solution(String msg) {
        // 사전 초기화
        dict = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            dict.put(String.valueOf(c), c - 'A' + 1);
        }
        
        // 핵심 로직
        List<Integer> answerList = new ArrayList<>();
        int start = 0, end = 0;
        while (start < msg.length()) {
            // tmp: 사전에 없는 새 문자열 후보
            String tmp = "";
            for (end = start; end < msg.length(); end++) {
                tmp = msg.substring(start, end + 1);
                if (!dict.containsKey(tmp)) break;
            }
            if (!dict.containsKey(tmp)) {
                dict.put(tmp, dict.size() + 1);
            }
            // msg.substring(start, end): 사전에 존재하는 가장 긴 문자열
            answerList.add(dict.get(msg.substring(start, end)));
            start = end;
        }
        
        // 배열로 변환
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}