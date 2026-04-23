import sys
import math

n = int(sys.stdin.readline())
lst = [int(sys.stdin.readline()) for _ in range(n)]
sorted(lst)
width_lst = [lst[i+1] - lst[i] for i in range(n-1)]

width = math.gcd(*width_lst)
res = sum(width_lst[i] // width - 1 for i in range(n-1))
print(res)
