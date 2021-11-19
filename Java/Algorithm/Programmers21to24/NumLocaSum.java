package Programmers21to24;

public class NumLocaSum {
    public int solution(int n) {
        int answer = 0;
        while(n>9){
            answer += n%10;
            n = n/10;
        }
        answer += n;


        System.out.println("Hello Java");

        return answer;
    }
}
