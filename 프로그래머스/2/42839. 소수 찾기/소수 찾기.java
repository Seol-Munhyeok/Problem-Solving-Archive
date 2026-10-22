import java.util.*;
class Solution {
    
    Set<Integer> set = new HashSet<>();
    int n;
    boolean[] visited;
    
    public int solution(String numbers) {
        n = numbers.length();
        visited = new boolean[n];
        
        dfs("", numbers);
        
        int answer = 0;
        for (int num : set) {
            if (isPrime(num)) {
                answer++;
            }
        }
        return answer;
    }
    
    private void dfs(String current, String numbers) {
        if (!current.isEmpty()) {
            set.add(Integer.parseInt(current));
        }
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(current + numbers.substring(i, i + 1), numbers);
            visited[i] = false;
        }   
    }
    
    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}