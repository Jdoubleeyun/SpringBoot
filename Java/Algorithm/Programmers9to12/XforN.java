package Programmers9to12;

public class XforN {
    public long[] solution(int x, int n){
        long[] answer = new long[n];
        long input = 0;
        for(int i=0; i<n; i++){
            input = (long)x * (i+1);
            answer[i] = input;
        }
        return answer;
    }
}
