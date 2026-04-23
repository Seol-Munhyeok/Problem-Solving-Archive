hour, minute = map(int, input().split())
total_min = hour * 60 + minute
new_hour = (total_min - 45) // 60
new_minute = (total_min - 45) % 60

if new_hour < 0:
    new_hour += 24
    
print(new_hour, new_minute)