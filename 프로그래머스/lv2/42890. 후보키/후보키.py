from itertools import combinations

def check_uniqueness(relation, subset):
    card = len(relation)
    check = []
    for i in range(card):
        temp = tuple(relation[i][j] for j in subset)
        check.append(temp)
    
    return len(check) == len(set(check))


def solution(relation):
    deg = len(relation[0])
    subsets = []
    for k in range(1, deg + 1):
        subsets.extend(combinations(range(deg), k))
    
    # 유일성 체크
    cands = []
    for subset in subsets:
        if check_uniqueness(relation, subset):
            cands.append(subset)
    
    # 최소성 체크
    result = []
    for cand in cands:
        is_minimal = True
        for key in result:
            if set(key).issubset(cand):
                is_minimal = False
                break
        if is_minimal:
            result.append(cand)
    
    return len(result)