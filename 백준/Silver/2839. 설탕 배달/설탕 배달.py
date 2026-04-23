# x : 3kg 봉지의 개수
# y : 5kg 봉지의 개수

sol_lst = []

n = int(input())
for x in range(n // 3 + 1):
    if (n - 3 * x) % 5 != 0:
        continue
    for y in range((n - 3 * x) // 5, -1, -1):
        if 3 * x + 5 * y == n:
            sol_lst.append(x + y)
            break

print(-1 if len(sol_lst) == 0 else min(sol_lst))
