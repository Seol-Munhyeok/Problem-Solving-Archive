import math

a, b, v = map(int, input().split())

res = math.ceil((v - b) / (a - b))
print(res)
