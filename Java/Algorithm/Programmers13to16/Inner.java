package Programmers13to16;

public class Inner {
    public int solution(int[] a, int[] b){
        int answer = 0;
        for(int i=0; i<a.length; i++){
            answer += a[i]*b[i];
        }
        return answer;
    }
}
