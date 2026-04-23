x = int(input())
y = int(input())


def isPostive(a):
    if a>0:
        return True
    else:
        return False


if isPostive(x):
    if isPostive(y):
        print("1")
    else:
        print("4")
else:
    if isPostive(y):
        print("2")
    else:
        print("3")