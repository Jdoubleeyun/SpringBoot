package Programmers5to8;

public class Summiplus {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 123456789;
        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0; i<absolutes.length; i++){
            if(signs[i] == false){
                sum1 -= absolutes[i];
            }
            else{
                sum2 += absolutes[i];
            }
            answer = sum1 + sum2;
        }
        return answer;
    }
}
