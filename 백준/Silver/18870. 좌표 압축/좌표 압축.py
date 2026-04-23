import sys

dic = dict()
n = int(sys.stdin.readline())
lst = list(map(int, sys.stdin.readline().split()))
sorted_lst = sorted(list(set(lst)))
max_num = len(set(lst))

for i in range(max_num):
     dic[sorted_lst[i]] = i

for i in range(n):
    lst[i] = dic[lst[i]]

print(*lst)
