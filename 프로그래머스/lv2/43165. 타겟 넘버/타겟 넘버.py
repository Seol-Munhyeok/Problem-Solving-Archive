def solution(numbers, target):
    def dfs(index, current_sum):
        if index == len(numbers):
            return 1 if current_sum == target else 0
        return dfs(index + 1, current_sum + numbers[index]) + \
               dfs(index + 1, current_sum - numbers[index])
        
    answer = dfs(0, 0)
    return answer