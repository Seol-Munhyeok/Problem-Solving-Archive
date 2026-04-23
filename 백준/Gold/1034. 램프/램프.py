import sys
from collections import defaultdict
n, m = map(int, input().split())
a = [list(map(int, input())) for _ in range(n)]
k = int(input())

zero_index = []
count = defaultdict(int)
for i in range(n):
    temp = []
    for j in range(m):
        if a[i][j] == 0:
            temp.append(j)
    count[tuple(temp)] += 1
#print(count)

max_answer = -1
zero_count = -1
for comb in zero_index:
    # 각 조합에 따라 스위치 ON
    for r in comb:
        for i in range(n):
            a[i][r] ^= 1

    # 켜져있는 행의 수 찾기
    answer = 0
    for row in a:
        if all(row):
            answer += 1
    if answer > max_answer:
        max_answer = answer
        zero_count = len(comb)

    # 원상복구
    for r in comb:
        for i in range(n):
            a[i][r] ^= 1

sorted_count = sorted(count.items(), key=lambda x: x[1], reverse=True)
for key, v in sorted_count:
    if k >= len(key) and k % 2 == len(key) % 2:
        print(v)
        sys.exit()
print(0)

