import sys
input = sys.stdin.readline

n = int(input())
w, h = 2*n-1, n

a = [[' ' for _ in range(w)] for _ in range(h)]

def draw(n, y, x):
    if n == 3:
        a[y][x] = a[y+1][x-1] = a[y+1][x+1] = '*'
        for i in range(-2, 3):
            a[y+2][x+i] = '*'
        return
    
    draw(n//2, y, x)
    draw(n//2, y + n//2, x - n//2)
    draw(n//2, y + n//2, x + n//2)
    
draw(n, 0, w//2)
for i in range(h):
    for j in range(w):
        print(a[i][j], end='')
    print()