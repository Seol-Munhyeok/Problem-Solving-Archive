import sys

lst = []
full_lst = []

for i in range(1, 31):
    full_lst.append(i)

for j in range(28):
    n = int(sys.stdin.readline())
    lst.append(n)

lst.sort()
for k in range(30):
    if full_lst[k] not in lst:
        print(full_lst[k])

