import sys

a, b, c = map(int, sys.stdin.readline().split())
max_edge = max(a, b, c)
sum_other_edge = a + b + c - max_edge

if sum_other_edge > max_edge:
    largest_perimeter = a + b + c
else:
    largest_perimeter = sum_other_edge * 2 - 1

print(largest_perimeter)
