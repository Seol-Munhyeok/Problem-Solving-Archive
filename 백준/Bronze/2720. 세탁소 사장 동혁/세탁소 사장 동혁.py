import sys


def get_change(change):
    quarter = change // 25
    tmp = change % 25

    dime = tmp // 10
    tmp = tmp % 10

    nickel = tmp // 5
    tmp = tmp % 5

    penny = tmp // 1
    tmp = tmp % 1

    return quarter, dime, nickel, penny


t = int(sys.stdin.readline())
for _ in range(t):
    change = int(sys.stdin.readline())
    print(*get_change(change))
