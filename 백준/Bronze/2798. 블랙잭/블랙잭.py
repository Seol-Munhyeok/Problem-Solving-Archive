import sys


n, m = map(int, sys.stdin.readline().split())
lst = list(map(int, sys.stdin.readline().split()))
sum_lst = []

for i in range(n):
    for j in range(i+1, n):
        if i == j:
            continue
        for k in range(j+1, n):
            if i == j or i == k or j == k:
                continue
            card_sum = lst[i] + lst[j] + lst[k]
            sum_lst.append(card_sum) if card_sum <= m else None

print(max(sum_lst))
