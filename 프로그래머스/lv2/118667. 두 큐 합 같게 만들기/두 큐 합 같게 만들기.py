from collections import deque

def solution(queue1, queue2):
    q1, q2 = deque(queue1), deque(queue2)
    s1, s2 = sum(q1), sum(q2)
    total = s1 + s2
    if total % 2: 
        return -1
    target = total // 2

    i = j = 0
    limit = 3 * (len(q1) + len(q2))
    cnt = 0
    while cnt <= limit:
        if s1 == target:
            return cnt
        if s1 > target:
            x = q1.popleft()
            s1 -= x; s2 += x
            q2.append(x)
            i += 1
        else:  # s1 < target
            x = q2.popleft()
            s2 -= x; s1 += x
            q1.append(x)
            j += 1
        cnt += 1
    return -1
