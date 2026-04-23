def solution(players, m, k):
    answer = 0
    alive = 0  # 증설된 서버의 수
    end = [0] * 100  # end[t] : t시에 반납되는 서버의 수
    
    for i, p in enumerate(players):
        need = p // m  # 필요한 서버의 수
        alive -= end[i]  # 반납되는 서버의 수 제외

        if alive < need:
            n = need - alive
            end[i + k] += n  # k 시간 후에 n 만큼 서버 반납 됨
            alive += n
            answer += n
            
    return answer