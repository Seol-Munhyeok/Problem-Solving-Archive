def make_matrix(rows, cols):
    arr = [[0 for j in range(cols)] for i in range(rows)]
    for i in range(rows):
        row_lst = list(map(int, input().split()))
        for j in range(cols):
            arr[i][j] = row_lst[j]
    return arr


rows, cols = map(int, input().split())
res_mat = [[0 for j in range(cols)] for i in range(rows)]
mat_1 = make_matrix(rows, cols)
mat_2 = make_matrix(rows, cols)

for i in range(rows):
    for j in range(cols):
        res_mat[i][j] = mat_1[i][j] + mat_2[i][j]

for r in res_mat:
    for num in r:
        print(num, end=' ')
    print()