import sys
gpa_sum, score_sum = 0, 0


def calculate_gpa(score, grade):
    if grade == 'A+':
        return score * 4.5
    elif grade == 'A0':
        return score * 4.0
    elif grade == 'B+':
        return score * 3.5
    elif grade == 'B0':
        return score * 3.0
    elif grade == 'C+':
        return score * 2.5
    elif grade == 'C0':
        return score * 2.0
    elif grade == 'D+':
        return score * 1.5
    elif grade == 'D0':
        return score * 1.0
    elif grade == 'F':
        return 0


for i in range(20):
    subject, score, grade = map(str, sys.stdin.readline().split())
    score = float(score)

    if grade != 'P':
        gpa_sum += calculate_gpa(score, grade)
        score_sum += score

res = gpa_sum / score_sum
print(res)


