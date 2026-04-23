from collections import deque

def solution(land):
    dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]
    n, m = len(land), len(land[0])
    visited = [[0 for _ in range(m)] for _ in range(n)]
    comp_id = 0
    for i in range(n):
        for j in range(m):
            if land[i][j] != 1 or visited[i][j]:
                continue
            # 1 (석유)를 발견하면 BFS 시작
            comp_id += 1
            queue = deque([(i, j)])
            visited[i][j] = comp_id
            cells = [(i, j)]
            
            while queue:
                y, x = queue.popleft()
                for k in range(4):
                    ny, nx = y + dy[k], x + dx[k]
                    if 0 <= ny < n and 0 <= nx < m and not visited[ny][nx] and land[ny][nx] == 1:
                        queue.append((ny, nx))
                        visited[ny][nx] = comp_id
                        cells.append((ny, nx))
            # 석유 컴포넌트의 값을 컴포넌트의 크기로 수정함
            size = len(cells)
            for y, x in cells:
                land[y][x] = size

    # 모든 열에 대해서 석유의 양을 계산
    answer = -1
    for i in range(m):
        seen = set()
        temp = 0
        for j in range(n):
            cid = visited[j][i]
            if cid != 0 and cid not in seen:
                seen.add(cid)
                temp += land[j][i]
        answer = max(answer, temp)
    
    return answer