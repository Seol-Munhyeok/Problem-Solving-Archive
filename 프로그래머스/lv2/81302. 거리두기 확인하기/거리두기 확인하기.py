from collections import deque

def solution(places):
    def in_range(y, x):
        return 0 <= y < 5 and 0 <= x < 5
    dirs = [(1,0),(-1,0),(0,1),(0,-1)]
    
    def bfs(place, sy, sx):
        q = deque([(sy, sx, 0)])
        vis = [[False] * 5 for _ in range(5)]
        vis[sy][sx] = True
        
        while q:
            y, x, d = q.popleft()
            for dy, dx in dirs:
                ny, nx, nd = y+dy, x+dx, d+1
                if not in_range(ny, nx) or vis[ny][nx] or nd > 2:
                    continue
                if place[ny][nx] == 'X':
                    continue
                if place[ny][nx] == 'P':   # 거리 ≤ 2 내 다른 사람
                    return False
                vis[ny][nx] = True
                q.append((ny, nx, nd))
        return True
    
    ans = []
    for place in places:
        ok = True
        for i in range(5):
            for j in range(5):
                if place[i][j] == 'P' and not bfs(place, i, j):
                    ok = False
                    break
            if not ok:
                break
        ans.append(1 if ok else 0)
    return ans