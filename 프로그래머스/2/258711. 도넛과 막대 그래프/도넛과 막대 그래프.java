class Solution {
    public int[] solution(int[][] edges) {
        // 진입차수, 진출차수 계산
        int MAX = 1_000_000;
        int[] inDegree = new int[MAX + 1];
        int[] outDegree = new int[MAX + 1];
        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }
        
        // 생성한 정점과 그래프 개수 구하기
        int graphCount = 0;
        int[] answer = new int[4];
        for (int i = 1; i <= MAX; i++) {
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                answer[0] = i;
                graphCount = outDegree[i];
            }
        }
        
        // 막대 모양, 8자 모양 그래프 개수 구하기
        for (int i = 1; i <= MAX; i++) {
            if (inDegree[i] > 0 && outDegree[i] == 0) {
                answer[2]++;
            } else if (i != answer[0] && outDegree[i] == 2) {
                answer[3]++;
            }
        }
        
        // 도넛 모양 그래프 개수 구하기
        answer[1] = graphCount - answer[2] - answer[3];
        
        return answer;
    }
}