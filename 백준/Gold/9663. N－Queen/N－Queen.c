#define MAX 15
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int queens(int level, int N);
bool promising(int level);

int N;
int main() {
    scanf("%d", &N);
    printf("%d", queens(0, N));
    return 0;
}

// 전제조건 : level개의 말이 이미 놓여있다.
// 놓여있는 말의 위치는 전역변수 cols[1, 2, ..., level]에 기록되어있다.
// 하는 일: 나머지 행들에 말을 놓아서 가능한 해의 개수를 카운트해서 개수를 리턴한다.
int cols[MAX + 1];

int queens(int level, int N) {
    if (!promising(level))
        return 0;
    /* promising 테스트를 통과했다는 가정하에 level==N이면 모든 말이 놓였다는 의미이고 따라서 성공이다.
    따라서 성공한 해의 개수 1을 리턴한다. */
    else if (level == N)
        return 1;

    int count = 0;
    /* level+1번째 말을 각각의 열에 놓은 후 recursion을 호출한다. */
    for (int i = 1; i <= N; i++) {
        cols[level + 1] = i;
        count += queens(level + 1, N);
    }
    return count;
}

bool promising(int level) {
    for (int i = 1; i < level; i++) {
        // 같은 열에 놓였는지 검사
        if (cols[i] == cols[level])
            return false;
        // 같은 대각선에 놓였는지 검사
        else if (level - i == abs(cols[level] - cols[i]))
            return false;
    }
    return true;
}