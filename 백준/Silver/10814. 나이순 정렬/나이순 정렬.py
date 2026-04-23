import sys

n = int(sys.stdin.readline())
member_lst = []

for _ in range(n):
    age, name = map(str, sys.stdin.readline().split())
    age = int(age)
    member_lst.append([age, name])

sorted_lst = sorted(member_lst, key=lambda x: x[0])

for item in sorted_lst:
    print(item[0], item[1])
