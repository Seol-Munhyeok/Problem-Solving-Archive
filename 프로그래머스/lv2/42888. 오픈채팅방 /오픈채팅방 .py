from collections import defaultdict

def solution(record):
    answer = []
    
    # 최종 닉네임 결정
    mp = defaultdict(str)
    for rec in record:
        r = rec.split()
        if len(r) == 3:
            mp[r[1]] = r[2]
            
    # 명령어에 맞춰서 출력
    for rec in record:
        r = rec.split()
        if r[0] == "Enter":
            answer.append(mp[r[1]] + "님이 들어왔습니다.")
        elif r[0] == "Leave":
            answer.append(mp[r[1]] + "님이 나갔습니다.")
            
    return answer