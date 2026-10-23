import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        List<Plan> planList = new ArrayList<>();
        for (String[] plan : plans) {
            Plan p = new Plan(plan[0], strToInt(plan[1]), Integer.parseInt(plan[2]));
            planList.add(p);
        }
        planList.sort((a, b) -> Integer.compare(a.start, b.start));
        
        int n = plans.length;
        String[] answer = new String[n];
        int idx = 0;
        Deque<Plan> stk = new ArrayDeque<>();
        for (int i = 0; i < n - 1; i++) {
            Plan current = planList.get(i);
            Plan next = planList.get(i + 1);
            if (current.start + current.playtime <= next.start) {
                answer[idx++] = current.name;
                int remain = next.start - (current.start + current.playtime);
                while (!stk.isEmpty() && remain > 0) {
                    Plan p = stk.peek();
                    if (remain >= p.playtime) {
                        stk.pop();
                        answer[idx++] = p.name;
                        remain -= p.playtime;
                    } else {
                        p.playtime -= remain;
                        remain = 0;
                    }  
                }  
            } else {
                current.playtime -= (next.start - current.start);
                stk.push(current);
            }
        }        
        
        answer[idx++] = planList.get(n - 1).name;
        while (!stk.isEmpty()) {
            Plan p = stk.pop();
            answer[idx++] = p.name;
        }
        
        return answer;
    }
    
    private int strToInt(String str) {
        int h = Integer.parseInt(str.substring(0, 2));
        int m = Integer.parseInt(str.substring(3));
        return 60 * h + m;
    }
    
    static class Plan {
        String name;
        int start, playtime;
        Plan(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }       
    }
}