/**
순서대로 n개의 퍼즐을 제한 시간 내에 풀어야 함
diffs = [1, 4, 4, 2] (diffs[0] = 1 보장)
times = [6, 3, 8, 2]
level = 2 (특정 값)으로 정해졌을 때,
    diff[i] ≤ level 이면 times[i] 만큼 소요
    diff[i] > level 이면 (times[i] + times[i - 1]) * (diff[i] - level) + times[i] 소요
전체 제한 시간 limit안에 통과해야 함
Return : 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도(level)의 최솟값

level = 1부터 1씩 증가하면서 처음으로 통과되는 순간이 정답
    1 ≤ diffs의 길이 = times의 길이 = n ≤ 300,000
    1 ≤ diffs[i] ≤ 100,000
    이므로 최악의 경우 300,000 * 100,000 번 탐색 (불가능)
level의 상한은 100,000이므로 1~100000 또는 1~max(diff[i]) 범위로 lowerBound를 구하면 됨
시간복잡도 O(300,000 * log(100,000))
*/

class Solution {
    
    int[] diffs;
    int[] times;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        
        int left = 1, right = 100_000;
        while (left < right) {
            int mid = (left + right) / 2;
            if (getTime(mid) <= limit) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
        
    private long getTime(int level) {
        long result = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) result += times[i];
            else result += (long) ((times[i] + times[i - 1]) * (diffs[i] - level) + times[i]);
        }
        return result;
    }
}