from collections import defaultdict

def solution(clothes):
    counts = defaultdict(int)
    
    for a, b in clothes:
        counts[b] += 1
        
    answer = 1
    for c in counts.values():
        answer *= (c + 1)
    return answer - 1