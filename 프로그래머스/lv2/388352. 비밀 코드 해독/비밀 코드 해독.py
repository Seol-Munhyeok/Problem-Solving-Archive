from itertools import combinations

def check(x, y):
    i = j = 0
    count = 0
    while i < len(x) and j < len(y):
        if x[i] == y[j]:
            count += 1
            i += 1
            j += 1
        elif x[i] < y[j]:
            i += 1
        else:
            j += 1
    
    return count
        
            
def solution(n, q, ans):
    answer = 0
    cands = combinations(list(range(1, n+1)), 5)
    for cand in cands:
        found = True
        for q1, a1 in zip(q, ans):
            if check(cand, q1) != a1:
                found = False
                break
        if found:
            answer += 1     
        
    return answer