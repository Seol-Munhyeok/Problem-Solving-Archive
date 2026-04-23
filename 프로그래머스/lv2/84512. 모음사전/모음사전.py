vowels = ['A', 'E', 'I', 'O', 'U']
answer = 0
found = False
def dfs(current, word):
        global answer, found
        if found:
            return
        
        if current:
            answer += 1
            if current == word:
                found = True
                return
            
        if len(current) == 5:
            return

        for v in vowels:
            dfs(current + v, word)
            
def solution(word):
    dfs('', word)
    
    

    return answer
