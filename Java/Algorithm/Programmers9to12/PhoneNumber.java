package Programmers9to12;

public class PhoneNumber {
    public String solution(String phone_number) {
        String answer = "";
        int len = phone_number.length();
        for(int i=0; i<len-4; i++){
            answer += "*";
        }
        for(int i= len-4; i<len; i++){
            answer += String.valueOf(phone_number.charAt(i));
        }
        return answer;
    }
}
