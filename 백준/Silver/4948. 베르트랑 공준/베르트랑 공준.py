import sys, math


def is_prime(number):
    # 2는 소수이므로 True 반환
    if number == 2:
        return True

    # 0, 1 또는 짝수인 경우 False 반환
    if number < 2 or number % 2 == 0:
        return False

    # 홀수만 검사함
    for i in range(3, math.isqrt(number) + 1, 2):
        if number % i == 0:
            return False
    return True


def cnt_prime(n):
    cnt = 0
    for num in range(n+1, 2 * n + 1):
        if is_prime(num):
            cnt += 1
    return cnt


while True:
    n = int(sys.stdin.readline())
    if n == 0:
        break
    print(cnt_prime(n))
