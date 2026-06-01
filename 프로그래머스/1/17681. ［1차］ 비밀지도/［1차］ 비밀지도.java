/**
벽 = 1, 공백 = 0
    어느 하나라도 1이면 전체에서 1
    모두 0인 부분은 전체에서 0
    => OR 연산
OR 연산 후 1='#', 0=' ' 으로 변경
*/
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = arr1[i] | arr2[i];
        }
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                if ( (arr[i] & (1 << j)) != 0 ) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}