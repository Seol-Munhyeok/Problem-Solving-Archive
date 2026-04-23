import sys

n = int(sys.stdin.readline())
lst, sorted_lst = [], []

for i in range(n):
    word = sys.stdin.readline().strip()
    lst.append(word)

lst = list(set(lst))    # 중복제거
sorted_lst = sorted(lst, key=lambda x: (len(x), x))

for words in sorted_lst:
    print(words)
