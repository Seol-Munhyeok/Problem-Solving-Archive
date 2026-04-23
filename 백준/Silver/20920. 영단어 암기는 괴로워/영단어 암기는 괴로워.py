import sys
from collections import Counter

word_frequency = Counter()
n, m = map(int, sys.stdin.readline().split())

for _ in range(n):
    word = sys.stdin.readline().strip()
    if len(word) >= m:
        word_frequency[word] += 1

res = sorted(word_frequency.keys(), key = lambda x : (-word_frequency.get(x), -len(x), x))
print("\n".join(res))
