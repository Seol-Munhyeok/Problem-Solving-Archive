import sys


class Stack:
    def __init__(self):
        self.items = []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def peek(self):
        return self.items[-1]

    def is_empty(self):    # 스택이 비었으면 True 리턴
        return not self.items

    def size(self):
        return len(self.items)


stk = Stack()


def process_command(command):
    parts = command.split()
    code = int(parts[0])

    if code == 1:
        num = int(parts[1])
        stk.push(num)

    if code == 2:
        try:
            print(stk.pop())
        except IndexError:
            print(-1)

    if code == 3:
        print(stk.size())

    if code == 4:
        if stk.is_empty():
            print(1)
        else:
            print(0)

    if code == 5:
        try:
            print(stk.peek())
        except IndexError:
            print(-1)


def main():
    n = int(sys.stdin.readline())
    for _ in range(n):
        command = sys.stdin.readline()
        process_command(command)


if __name__ == "__main__":
    main()


