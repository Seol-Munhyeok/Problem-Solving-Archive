import sys

n, m = map(int, sys.stdin.readline().split())
d = set()    # 듣도 못한 사람
b = set()    # 보도 못한 사람

for _ in range(n):
    name = sys.stdin.readline().strip()
    d.add(name)

for _ in range(m):
    name = sys.stdin.readline().strip()
    b.add(name)

db = d.intersection(b)    # 듣도 보도 못한 사람
res = sorted(list(db))
print(len(res))
[print(name) for name in res]
