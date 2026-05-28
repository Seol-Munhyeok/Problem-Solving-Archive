import java.util.*;
/**
Return : 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열
실패율 = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
stages.length = 전체 사용자 수 
count 배열로 사용자가 현재 도전 중인 스테이지의 개수를 구함
특정 stage의 실패율 = count[stage] / stages.length
    stages.length -= count[stage];
*/

class Solution {
    public int[] solution(int N, int[] stages) {
        
        int totalPlayers = stages.length;
        
        // count 배열 생성
        int[] count = new int[N + 2];
        for (int i = 0; i < totalPlayers; i++) {
            count[stages[i]]++;
        }
        
        // 각 스테이지별 실패율 구하기
        List<Node> lst = new ArrayList<>();
        for (int stage = 1; stage <= N; stage++) {
            double rate = totalPlayers == 0 ? 0 : (double)count[stage] / totalPlayers;
            lst.add(new Node(stage, rate));
            totalPlayers -= count[stage];
        }
        
        lst.sort(null);
               
        int[] answer = new int[N];
        int idx = 0;
        for (Node n : lst) {
            answer[idx++] = n.stage;
        }     
        return answer;
    }
    
    static class Node implements Comparable<Node> {
        int stage;
        double rate;
        Node (int stage, double rate) {
            this.stage = stage;
            this.rate = rate;
        }
        
        @Override
        public int compareTo(Node other) {
            if (this.rate == other.rate) {
                return Integer.compare(this.stage, other.stage); // stage 오름차순
            }
            return Double.compare(other.rate, this.rate); // rate 내림차순
        }
    }
}