lst = []

for i in range(10):
    remainder = int(input()) % 42
    lst.append(remainder)

print(len(set(lst)))