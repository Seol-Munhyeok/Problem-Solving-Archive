def go(a, b):
    if a > b:
        a, b = b, a
    while True:
        while b > a:
            b //= 2
        if b == a:
            return 10 * a
        while a > b:
            a //= 2
    
t = int(input())
for _ in range(t):
    a, b = map(int, input().split())
    print(go(a, b))