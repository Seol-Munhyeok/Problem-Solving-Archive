import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int[] leftDays = new int[n];
        for (int i = 0; i < n; i++) {
            leftDays[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
        }
        
        List<Integer> answerList = new ArrayList<>();   
        int topIdx = 0;
        for (int i = 1; i < n; i++) {
            if (leftDays[topIdx] < leftDays[i]) {
                answerList.add(i - topIdx);
                topIdx = i;
            }
        }
        answerList.add(n - topIdx);
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}