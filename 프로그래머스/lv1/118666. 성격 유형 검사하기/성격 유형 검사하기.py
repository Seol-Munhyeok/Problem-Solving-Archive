def solution(survey, choices):
    result = {'R': 0, 'T': 0, 'C': 0, 'F': 0, 'J': 0, 'M': 0, 'A': 0, 'N': 0}
    for s, c in zip(survey, choices):
        if c == 4:
            continue
        elif c <= 3:
            result[s[0]] += (4 - c)
        else:
            result[s[1]] += (c - 4)
    
    answer = ''
    pairs = [('R', 'T'), ('C', 'F'), ('J', 'M'), ('A', 'N')]
    for a, b in pairs:
        if result[a] > result[b]:
            answer += a
        elif result[a] < result[b]:
            answer += b
        else:
            answer += min(a, b)
            
    return answer