from collections import Counter
    
def solution(board):
    # O와 X 개수
    count = Counter("".join(board))
    # 세로줄을 가로줄로 바꾼 배열
    tranposed = []
    for j in range(3):
        new_row = ""
        for i in range(3):
            new_row += board[i][j]
        tranposed.append(new_row)
    tmp = board + tranposed
    # 대각선
    dia1 = board[0][0] + board[1][1] + board[2][2]
    tmp.append(dia1)
    dia2 = board[0][2] + board[1][1] + board[2][0]
    tmp.append(dia2)
    # 빙고 개수
    o_win = tmp.count("OOO")
    x_win = tmp.count("XXX")
    
    # O 갯수가 X 갯수와 같을때, O 빙고가 아니라면 성공
    if count['O'] == count['X'] and o_win == 0:
        return 1
    
    # O 갯수가 X 갯수보다 1개 많을때, X 빙고가 아니라면 성공
    if count['O'] - count['X'] == 1 and x_win == 0:
        return 1
    
    # 이외에는 모두 실패
    return 0