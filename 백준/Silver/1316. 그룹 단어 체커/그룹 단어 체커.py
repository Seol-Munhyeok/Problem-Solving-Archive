def check(str):
    dic = dict()
    i, j = 0, 0
    while j < len(str):
        if str[i] in dic:
            return False
        dic[str[i]] = 1
        while j < len(str) and str[i] == str[j]:
            j += 1
        i = j

    return True


n = int(input())
answer = 0
for _ in range(n):
    str = input()
    if check(str):
        answer += 1

print(answer)