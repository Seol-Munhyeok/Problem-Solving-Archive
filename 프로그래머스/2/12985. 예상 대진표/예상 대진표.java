class Solution {
    public int solution(int n, int a, int b) {
        int answer = 0;        
        while (a != b) {
            a = (a + 1) / 2;  // 올림 처리
            b = (b + 1) / 2;
            answer++;
        }      
        return answer;
    }
}