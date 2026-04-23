word = input().upper()
char_lst = list(set(word))
dic = dict()

for keys in char_lst:
    dic[keys] = 0

for alphabet in word:
    dic[alphabet] += 1

most = max(dic.values())
if list(dic.values()).count(most) == 1:
    rev_dic = {v: k for k, v in dic.items()}
    print(rev_dic.get(most))
else:
    print("?")