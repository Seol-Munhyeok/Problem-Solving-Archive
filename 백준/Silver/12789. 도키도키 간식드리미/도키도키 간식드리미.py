import sys

n = int(sys.stdin.readline())
wait = list(map(int, sys.stdin.readline().split()))
stack = []

target = 1
for student in wait:
    stack.append(student)
    while stack and stack[-1] == target:
        stack.pop()
        target += 1
    if len(stack) >= 2 and stack[-1] > stack[-2]:
        print('Sad')
        sys.exit()
if stack:
    print('Sad')
else:
    print('Nice')
