from itertools import combinations

def solve(dice_set):
    n = len(dice_set)
    max_value = sum(max(d) for d in dice_set)
    dp = [[0] * (max_value+1) for _ in range(n+1)]
    dp[0][0] = 1
    
    for i, faces in enumerate(dice_set, start=1):
        for s in range(max_value+1):
            for v in faces:
                if s + v <= max_value:
                    dp[i][s + v] += dp[i-1][s]
    
    return dp[n]
    
def solution(dice):
    answer = []
    max_count = -1
    n = len(dice)
    combs = combinations(range(n), n // 2)
    
    for comb in combs:
        # A, B 주사위 선택
        comb_set = set(comb)
        a_dice = [dice[i] for i in range(n) if i in comb_set]
        b_dice = [dice[i] for i in range(n) if i not in comb_set]

        # A, B가 만들 수 있는 주사위 합의 경우의 수 구하기
        A = solve(a_dice)
        B = solve(b_dice)
        
        # B 누적합 배열 생성
        m = max(len(A), len(B))
        b_sum = [0] * (m)
        
        if len(B) > 0:
            b_sum[0] = B[0]
        for i in range(1, m):
            b_sum[i] = b_sum[i-1] + (B[i] if i < len(B) else 0)
                
        # A가 이기는 경우의 수 계산
        count = 0
        for i in range(1, len(A)):
            if A[i]:
                count += A[i] * b_sum[i-1]
        
        # 최댓값 갱신
        if count > max_count:
            answer = comb
            max_count = count
    
    return [x + 1 for x in answer]  # 1-based
