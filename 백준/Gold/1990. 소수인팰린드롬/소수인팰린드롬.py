import math, sys

n, m = map(int, sys.stdin.readline().split())
if n % 2 == 0:
    n += 1
if m > 10000000:
    m = 10000000


def is_palindrome(num):
    num_str = str(num)
    # 팰린드롬 수의 길이가 짝수인 경우 25377352와 같다면 11, 1001, 100001 등이
    # 모두 11의 배수이기 때문에 11 하나를 제외하고는 짝수 길이 팰린드롬은 소수가 될 수 없습니다.
    # 이 최적화를 하면 b의 범위가 최대 10,000,000으로 제한되는 것과 같게 되므로 수행시간이 1/10 정도로 감소합니다.
    if num != 11 and len(num_str) % 2 == 0:
        return False
    return num_str == num_str[::-1]


def is_prime(number):
    # 입력이 5이상인 정수이므로, 홀수만 검사함
    for i in range(3, int(math.sqrt(number)) + 1, 2):
        if number % i == 0:
            return False
    return True


for num in range(n, m+1, 2):
    if is_palindrome(num) and is_prime(num):
        print(num)
print(-1)
