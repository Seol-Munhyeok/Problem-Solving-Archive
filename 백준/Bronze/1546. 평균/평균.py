n = int(input())
lst = list(map(float, input().split()))
changed_lst = []


def avg(arr):
    return sum(arr) / len(arr)


def change_score(num, arr):
    return num / max(arr) * 100


for i in range(n):
    changed_lst.append(change_score(lst[i], lst))


print(avg(changed_lst))
