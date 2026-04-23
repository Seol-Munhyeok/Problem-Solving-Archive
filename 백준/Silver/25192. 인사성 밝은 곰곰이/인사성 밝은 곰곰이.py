import sys

n = int(sys.stdin.readline())

emoted_user = set()
sys.stdin.readline().strip()    # 첫 번째 문자열은 'ENTER'로 받음
cnt, res = 0, 0

while True:
    chat = sys.stdin.readline().strip()
    cnt += 1
    if chat == 'ENTER':
        res += len(emoted_user) - 1
        emoted_user = set()
    emoted_user.add(chat)
    if cnt == n - 1:
        res += len(emoted_user)
        break

print(res)
