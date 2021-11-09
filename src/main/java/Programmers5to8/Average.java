package Programmers5to8;

public class Average {
    public double solution(int[] arr) {
        double answer = 0;
        double sum = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
        }
        answer = sum/arr.length; // int 와 int 를 연산하면 결과값은
        // 실수형이여도 소수점은 제외하고 정수부분만 출력됨.
        // int 형 double  형으로 넣기 가능.
        return answer;
    }
}
