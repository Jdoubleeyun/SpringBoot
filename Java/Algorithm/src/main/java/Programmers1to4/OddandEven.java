package Programmers1to4;

public class OddandEven {
    public String main(int num){
        //return 값이 string 이기 때문에
        //static void(System.out.println) 대신 String 사용.
        String answer = ""; // String 초기값 설정.
        if(num%2 == 0){
            answer = "Even";
        }
        else{
            answer = "Odd";
        }
        return answer;
    }
}
