def solution(citations):
    answer = 0
    
    def check(i):
        count = 0
        for c in citations:
            if c >= i:
                count += 1
        return count >= i
    
    for i in range(1, len(citations) + 1):
        if check(i):
            answer = max(answer, i)
               
    return answer