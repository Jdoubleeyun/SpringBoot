package Programmers1to4;

public class MiddleNum {
    public long solution(int a, int b) {
        long answer = 0;

        if(a<b){
            for(int i=a; i<=b; i++){
                answer +=i;
            }
        }
        else{
            for(int i=b; i<=a; i++){
                answer +=i;
            }
        }
        return answer;
    }
}
