package Programmers9to12;

public class CostSum {
    public long solution(int price, int money, int count) {
        long answer = 0;
        long total = 0;
        for(int i=0; i<count; i++){
            total += price * (i+1);
        }
        if(total > money){
            answer = total - money;
        } else {answer = 0;}
        return answer;
    }
}
