def is_prime(n):
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False

    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i+2) == 0:
            return False
        i += 6

    return True


def is_palindrome(num):
    num_str = str(num)
    return num_str == num_str[::-1]


n = int(input())
if n == 10 or n == 11:
    print(11)
elif len(str(n)) % 2 == 0:
    n = 10 ** len(str(n))    # 11을 제외한 짝수 자리 소수 펠린드롬은 없음

if n != 10 and n != 11:
    while True:
        if is_palindrome(n) and is_prime(n):
            print(n)
            break
        else:
            n += 1
