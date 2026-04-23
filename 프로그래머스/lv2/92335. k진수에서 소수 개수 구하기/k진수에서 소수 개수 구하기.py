def conversion(n, base):
    res = ""
    while n:
        res += str(n % base)
        n //= base
    return res[::-1]


def is_prime(n):
    if n < 2:
        return False
    i = 2
    while i * i <= n:
        if n % i == 0:
            return False
        i += 1
    return True
        
        
def solution(n, k):
    answer = 0
    cands = conversion(n, k).split('0')
    for cand in cands:
        if not cand: continue
        if is_prime(int(cand)):
            answer += 1
            
    return answer