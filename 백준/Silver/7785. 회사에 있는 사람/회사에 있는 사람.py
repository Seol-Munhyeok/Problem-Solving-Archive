import sys


n = int(sys.stdin.readline())
access_logs = {}
for _ in range(n):
    name, status = map(str, sys.stdin.readline().split())
    access_logs[name] = status
    if status == 'leave':
        del access_logs[name]

for key in sorted(access_logs.keys(), reverse=True):
    print(key)
