x1, y1 = map(int, input().split())
x2, y2 = map(int, input().split())
x3, y3 = map(int, input().split())

min_x = min(x1, x2, x3)
max_x = max(x1, x2, x3)
min_y = min(y1, y2, y3)
max_y = max(y1, y2, y3)

point_set = {(min_x, min_y), (min_x, max_y), (max_x, min_y), (max_x, max_y)}
my_set = {(x1, y1), (x2, y2), (x3, y3)}
my_point = point_set - my_set

for item in my_point:
    print(item[0], item[1])
    