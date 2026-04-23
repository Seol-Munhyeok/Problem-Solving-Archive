import sys

n = 10000
is_prime = [True] * (n + 1)    # 처음엔 모든 수가 소수(True)인 것으로 초기화
for i in range(2, int(n ** 0.5) + 1):
    if is_prime[i]:    # i가 소수인 경우
        # i의 배수들을 바로 False로 설정하여 소수를 제거
        is_prime[i * i: n + 1: i] = [False] * len(range(i * i, n + 1, i))

prime_lst = [i for i in range(2, n+1) if is_prime[i]]    # 10000 이하의 소수를 담은 배열


def is_prime(number):
    # 2는 소수이므로 True 반환
    if number == 2:
        return True

    # 0, 1 또는 짝수인 경우 False 반환
    if number < 2 or number % 2 == 0:
        return False

    # 홀수만 검사함
    for i in range(3, int(n ** 0.5) + 1, 2):
        if number % i == 0:
            return False
    return True


def gb_partition(n):
    min_diff = float('inf')    # 차이의 최소값 초기화
    res = None
    start_value = n // 2

    while not is_prime(start_value):
        start_value -= 1

    index = prime_lst.index(start_value)
    for i in range(index, -1, -1):
        if is_prime(n - prime_lst[i]):
            diff = abs(n - 2 * prime_lst[i])
            if diff < min_diff:
                min_diff = diff
                res = (prime_lst[i], n - prime_lst[i])
    return res


t = int(sys.stdin.readline())
for _ in range(t):
    n = int(sys.stdin.readline())
    print(*gb_partition(n))
