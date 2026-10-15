class Solution {
    
    int n, target, answer;
    int[] numbers;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        n = numbers.length;
        answer = 0;
        dfs(0, 0);
        return answer;
    }
    
    private void dfs(int idx, int sum) {
        if (idx == n) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        
        dfs(idx + 1, sum + numbers[idx]);
        dfs(idx + 1, sum - numbers[idx]);
    }
}