import sys
from collections import deque

q = deque()


def process_command(command):
    parts = command.split()
    cmd = parts[0]

    if cmd == 'push':
        num = parts[1]
        q.append(num)

    if cmd == 'pop':
        try:
            print(q.popleft())
        except IndexError:
            print(-1)

    if cmd == 'size':
        print(len(q))

    if cmd == 'empty':
        if q:
            print(0)
        else:
            print(1)

    if cmd == 'front':
        try:
            print(q[0])
        except IndexError:
            print(-1)

    if cmd == 'back':
        try:
            print(q[-1])
        except IndexError:
            print(-1)


n = int(sys.stdin.readline())
for _ in range(n):
    command = sys.stdin.readline().rstrip()
    process_command(command)
