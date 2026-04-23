import sys


n = int(sys.stdin.readline())
my_cards = list(map(int, sys.stdin.readline().split()))

card_dict = {}

for card in my_cards:
    card_dict[card] = True


m = int(sys.stdin.readline())
target_numbers = list(map(int, sys.stdin.readline().split()))

for target in target_numbers:
    if card_dict.get(target):
        print(1, end=' ')
    else:
        print(0, end=' ')
