x, y, w, h = map(int, input().split())

min_value = min(x, w-x, y, h-y)
print(min_value)
