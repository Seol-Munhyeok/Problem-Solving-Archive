from collections import deque
import sys

n, k = map(int, sys.stdin.readline().split())
queue = deque([i for i in range(1, n+1)])
josephus = []

for _ in range(n):
    queue.rotate(-(k-1))
    josephus.append(str(queue.popleft()))

print(f"<{', '.join(josephus)}>")
