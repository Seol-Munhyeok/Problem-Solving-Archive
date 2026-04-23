lst = [1, 1, 2, 2, 2, 8]
my_lst = list(map(int, input().split()))
tmp = []

for i in range(6):
    tmp.append(lst[i] - my_lst[i])

print(*tmp)
