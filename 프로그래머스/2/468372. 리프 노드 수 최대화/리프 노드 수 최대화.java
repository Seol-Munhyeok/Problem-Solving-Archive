class Solution {
    public int solution(int dist_limit, int split_limit) {
        long d = dist_limit, s = split_limit;
        long answer = 1;
        
        // i = 2분배 층 개수
        for (int i = 0; i <= 30; i++) {
            long pow2 = pow(2, i);

            // j = 3분배 층 개수
            for (int j = 0; j <= 20; j++) {
                long pow3 = pow(3, j);

                // 분배도 제한 확인
                if (pow2 * pow3 > s) continue;

                // 2분배 i층을 완전히 채웠을 때 필요한 2분배 노드 수
                long d2 = Math.min(d, pow2 - 1);

                // 3분배 노드는 남은 예산만큼만 놓을 수 있음
                long d3 = Math.min(d - d2, gSum(pow2, 3, j));

                // 리프 수 = 기본 1 + 2분배 노드 수 + 3분배 노드 수 * 2
                long leafCount = 1 + d2 + d3 * 2;

                answer = Math.max(answer, leafCount);
            }
        }
        
        return (int) answer;
    }
    
    private long pow(long a, long b) {
        long res = 1;
        while ((b--) > 0) res *= a;
        return res;
    }
    
    private long gSum(long a, long r, long n) {
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += a;
            a *= r;
        }
        return res;
    }
}