from itertools import combinations

def solution(friends, gifts):
    n = len(friends)
    mp = {}
    table = [[0 for _ in range(n)] for _ in range(n)]  # 주고 받은 선물 기록
    point = [0] * n  # 선물 지수 기록
    gift_count = [0] * n  # 다음 달에 받을 선물 개수
    
    for i, name in enumerate(friends):
        mp[name] = i
    
    # 주고 받은 선물 기록
    for gift in gifts:
        A, B = gift.split()
        table[mp[A]][mp[B]] += 1
    
    # 선물 지수 기록
    for i in range(n):
        give_count, receive_count = 0, 0
        for j in range(n):
            give_count += table[i][j]
        for j in range(n):
            receive_count += table[j][i]
        point[i] = give_count - receive_count
    
    for i, j in combinations(range(n), 2):
        # 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면
        if table[i][j] == table[j][i]:
            # 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
            if point[i] > point[j]:
                gift_count[i] += 1
            elif point[i] < point[j]:
                gift_count[j] += 1    
        # 두 사람이 선물을 주고받은 기록이 있다면
        else:
            # 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
            if table[i][j] > table[j][i]:
                gift_count[i] += 1
            elif table[i][j] < table[j][i]:
                gift_count[j] += 1
                
    return max(gift_count)