import java.util.*;
/** 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이를 return
1 <= len(s) <= 1000
naive
문자열을 1개 ~ len(s) / 2까지 다 잘라서 list에 담기 -> O(N^2)
list를 다시 순회하면서 앞과 뒤가 같으면 cnt 증가 -> O(N)
달라지는 순간 cnt와 해당 문자열을 붙이고, cnt = 0으로 다시 순회

len(s) == 1 인 경우 조심!!

*/
class Solution {
    public int solution(String s) {
        int N = s.length();
        if (N == 1) return 1;  // 문자열의 길이가 1인 경우 예외 처리
        
        int answer = 1_000_000_000;
        for (int len = 1; len <= N / 2; len++) {
            List<String> wordList = getSplitList(s, len);
            String compressedWord = compress(wordList);
            answer = Math.min(answer, compressedWord.length());
        }
        return answer;
    }
    
    private String compress(List<String> words) {
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        
        for (int i = 0; i < words.size(); i++) {
            if (i < words.size() - 1 && words.get(i).equals(words.get(i + 1))) {
                cnt++;
            } else {
                if (cnt > 1) sb.append(cnt);
                sb.append(words.get(i));
                cnt = 1;
            }
        }

        return sb.toString();
    }
    
    private List<String> getSplitList(String s, int len) {
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i += len) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len && i + j < s.length(); j++) {
                sb.append(s.charAt(i + j));
            }
            res.add(sb.toString());
        }
        return res;
    }
}