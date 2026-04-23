import sys
from collections import deque

n, k = map(int, sys.stdin.readline().split())
q = deque(str(num) for num in range(1, n+1))
perm = []

while q:
    q.rotate(-(k-1))
    perm.append(q.popleft())

print(f"<{', '.join(perm)}>")
