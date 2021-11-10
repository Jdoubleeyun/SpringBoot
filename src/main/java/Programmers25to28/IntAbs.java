package Programmers25to28;

public class IntAbs {
    public long solution(long n) {
        long answer = 0;
        double root = Math.sqrt(n);
        long rot = (long)Math.floor(root);
        if(root == rot){
            answer = (long)Math.pow(rot+1, 2);
        }else{answer =-1;}
        return answer;
    }
}
