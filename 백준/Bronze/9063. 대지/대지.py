import sys

x_coords = []
y_coords = []

n = int(sys.stdin.readline())
for _ in range(n):
    x, y = map(int, sys.stdin.readline().split())
    x_coords.append(x)
    y_coords.append(y)

res = (max(x_coords) - min(x_coords)) * (max(y_coords) - min(y_coords))
print(res)
