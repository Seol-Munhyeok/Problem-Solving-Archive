import datetime
x, y = map(int, input().split())
date = datetime.date(2007, x, y)
date = date.weekday()
if date == 0:
    print("MON")
if date == 1:
    print("TUE")
if date == 2:
    print("WED")
if date == 3:
    print("THU")
if date == 4:
    print("FRI")
if date == 5:
    print("SAT")
if date == 6:
    print("SUN")
    