def decomp_sum_calculator(num):
    tmp_sum = 0
    tmp = num
    while num != 0:
        tmp_sum += num % 10
        num //= 10
    return tmp + tmp_sum


n = int(input())
for i in range(1, n+1):
    res = decomp_sum_calculator(i)
    if res == n:
        print(i)
        break

    # 생성자가 없는 경우
    if i == n:
        print(0)
