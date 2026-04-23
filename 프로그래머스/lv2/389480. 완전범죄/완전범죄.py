def solution(info, n, m):
    
    INF = int(1e9)
    N = len(info)
    capA = min(n-1, 3*N)  # A 흔적 합 상한
    # dp[i][a] = 앞에서 i개 처리했고,
    # A의 누적 흔적이 a일 때 달성 가능한 B의 흔적 최소 합
    dp = [[INF for _ in range(capA + 1)] for _ in range(N + 1)]
    dp[0][0] = 0
    
    for i in range(N):
        a_i, b_i = info[i][0], info[i][1]
        for a in range(capA + 1):
            if dp[i][a] >= INF:
                continue
            # i를 A가 훔치는 경우
            na = a + a_i
            if na < n and na <= capA:
                dp[i + 1][na] = min(dp[i + 1][na], dp[i][a])
            # i를 B가 훔치는 경우
            nb_sum = dp[i][a] + b_i
            if nb_sum < m:
                dp[i + 1][a] = min(dp[i + 1][a], nb_sum)
    
    answer = INF
    for a in range(capA + 1):
        if dp[N][a] < m:
            answer = min(answer, a)

    return answer if answer != INF else -1