def solution(n, computers):
    visited = [False] * n
    
    def dfs(node):
        visited[node] = True
        for next_node in range(n):
            if not visited[next_node] and computers[node][next_node] == 1:
                dfs(next_node)
            
    answer = 0
    for i in range(n):
        if not visited[i]:
            dfs(i)
            answer += 1
            
    return answer