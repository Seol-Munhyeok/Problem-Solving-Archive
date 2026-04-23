import sys


n = int(sys.stdin.readline())
my_cards =  sorted(list(map(int, sys.stdin.readline().split())))
m = int(sys.stdin.readline())
target_numbers =list(map(int, sys.stdin.readline().split()))


def binary_search(lst, target):
    start_index = 0
    end_index = len(lst) - 1

    while start_index <= end_index:
        mid_index = (start_index + end_index) // 2

        if lst[mid_index] == target:
            return 1

        if lst[mid_index] < target:
            start_index = mid_index + 1
        else:
            end_index = mid_index - 1

    return 0


for idx in range(m):
    print(binary_search(my_cards, target_numbers[idx]), end=' ')
