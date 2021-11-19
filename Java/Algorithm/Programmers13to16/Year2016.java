package Programmers13to16;

public class Year2016 {
    public String solution(int a, int b) {
        String answer = "";
        int sumDays = 0;
        int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] Day = {"THU","FRI","SAT","SUN","MON","TUE","WED"};
        sumDays += b;
        for(int i=0; i<a-1; i++){
            sumDays += days[i];
        }
        answer = Day[sumDays%7];
        return answer;
    }
}
