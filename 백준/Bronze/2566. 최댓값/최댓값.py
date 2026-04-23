arr = [list(map(int, input().split())) for _ in range(9)]
max_of_rows = [max(arr[i]) for i in range(9)]

max_value = max(max_of_rows)
res_row = max_of_rows.index(max_value)
res_col = arr[res_row].index(max_value)

print(max_value)
print(res_row + 1, res_col + 1)