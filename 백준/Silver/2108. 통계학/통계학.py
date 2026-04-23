import sys
from collections import Counter

n = int(sys.stdin.readline())
data = [int(sys.stdin.readline()) for _ in range(n)]
data_cnt = Counter(data)


def mean(arr):
    return round(sum(arr) / len(arr))


def median(arr):
    mid_idx = len(arr) // 2
    return sorted(arr)[mid_idx]


def mode(arr):
    order = data_cnt.most_common()
    maximum = order[0][1]
    modes = []

    for num in order:
        if num[1] == maximum:
            modes.append(num)
            
    if len(modes) == 1:
        return modes[0][0]
    else:
        modes.sort(key = lambda x: x[0])
        return modes[1][0]


def span(arr):
    return max(arr) - min(arr)


print(mean(data))
print(median(data))
print(mode(data))
print(span(data))
