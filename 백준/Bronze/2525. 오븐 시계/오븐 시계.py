hour, minute = map(int, input().split())
cook_time = int(input())
total_min = hour * 60 + minute
new_hour = (total_min + cook_time) // 60
new_minute = (total_min + cook_time) % 60

if new_hour >= 24:
    new_hour -= 24

print(new_hour, new_minute)