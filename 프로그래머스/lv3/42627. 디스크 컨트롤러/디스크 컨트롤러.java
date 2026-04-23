import java.util.*;

class Solution {
    static class Job {
        int id;
        int requestTime;
        int duration;
        
        Job(int id, int requestTime, int duration) {
            this.id = id;
            this.requestTime = requestTime;
            this.duration = duration;
        }
    }
    
    public int solution(int[][] jobs) {
        PriorityQueue<Job> pq =
            new PriorityQueue<>(
                Comparator.comparingInt((Job j) -> j.duration)
                          .thenComparingInt(j -> j.requestTime)
                          .thenComparingInt(j -> j.id)
            );
        
        int n = jobs.length;
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int idx = 0;
        int t = 0;
        int turnaroundTime = 0;
        
        while (idx < n || !pq.isEmpty()) {
            
            while (idx < n && jobs[idx][0] <= t) {
                pq.offer(new Job(idx, jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if (pq.isEmpty()) {
                t = jobs[idx][0];
            }
            else {
                Job cur = pq.poll();
                t += cur.duration;
                turnaroundTime += (t - cur.requestTime);
            }
        }
        
        return turnaroundTime / n;
    }
}