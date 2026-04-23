def date_to_number(date):
    y, m, d = date.split(".")
    return (int(y) - 2000) * 336 + (int(m) - 1) * 28 + int(d)

def solution(today, terms, privacies):
    answer = []
    # 약관 : 개월 map 저장
    mp = {}
    for term in terms:
        t, month = term.split()
        mp[t] = int(month)
    
    today = date_to_number(today)
    # 날짜 + 개월 후에 날짜를 계산
    for i, privacy in enumerate(privacies):
        date, term = privacy.split()
        expired = date_to_number(date) + mp[term] * 28
        if expired <= today:
            answer.append(i + 1)
            
    return answer