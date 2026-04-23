n, b = map(int, input().split())
remainder_lst = []

if n < b:
    remainder_lst.append(n)
else:
    while True:
        quotient = n // b
        remainder = n % b
        remainder_lst.insert(0, remainder)
        if quotient < b:
            remainder_lst.insert(0, quotient)
            break
        n = quotient

# 10 이상의 나머지는 알파벳으로 변환
remainder_lst = [num if num <= 9 else chr(num + 55) for num in remainder_lst]

res = ''.join(map(str, remainder_lst))
print(res)
