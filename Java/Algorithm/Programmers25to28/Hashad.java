package Programmers25to28;

public class Hashad {
    public boolean solution(int x) {
        boolean answer = true;
        long sum =0;
        int n = x;
        while(x>9){
            sum += x%10;
            x = x/10;
        }
        sum += x;
        if(n%sum == 0){
            answer = true;
        }else{answer = false;}
        return answer;
    }
}


