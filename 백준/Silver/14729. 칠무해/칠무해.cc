#include <bits/stdc++.h>
using namespace std;

int N;
double d;
int main(){
    priority_queue<double, vector<double>, greater<double>> pq; 
    cin >> N;
    while (N--){
        cin >> d;
        pq.push(d);
    }
    cout << fixed;
    cout.precision(3);
    for (int i = 0; i < 7; i++){
        cout << pq.top() << '\n';
        pq.pop();
    }
    return 0;
}