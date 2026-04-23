import sys


def get_prime_list(n):
    lst = [False, False] + [True] * (n - 1)
    for i in range(2, int(n ** 0.5) + 1):
        if lst[i]:
            for k in range(i * i, n + 1, i):
                lst[k] = False
    return lst


primes = get_prime_list(1000000)


def get_partition_count(m):
    cnt = 0
    for i in range(2, m // 2 + 1):
        if primes[i] and primes[m - i]:
            cnt += 1
    return cnt


t = int(sys.stdin.readline())
for _ in range(t):
    m = int(sys.stdin.readline())
    print(get_partition_count(m))
