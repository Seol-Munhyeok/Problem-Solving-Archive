from collections import deque

def solution(maps):
    n, m = len(maps), len(maps[0])
    visited = [[False] * m for _ in range(n)]
    dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]
    
    queue = deque()
    queue.append((0, 0))
    visited[0][0] = True
    distance = [[1] * m for _ in range(n)]  # 시작점 1
    
    while queue:
        y, x = queue.popleft()
        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if 0 <= ny < n and 0 <= nx < m:
                if not visited[ny][nx] and maps[ny][nx] == 1:
                    visited[ny][nx] = True
                    distance[ny][nx] = distance[y][x] + 1
                    queue.append((ny, nx))
    
    return distance[n - 1][m - 1] if visited[n - 1][m - 1] else -1