import math

lst = list(map(int, input().split()))
mn = int(1e9)

for i in range(0, 5):
    for j in range(i + 1, 5):
        for k in range(j + 1, 5):
            mn = min(mn, math.lcm(math.lcm(lst[i], lst[j]), lst[k]))

print(mn)