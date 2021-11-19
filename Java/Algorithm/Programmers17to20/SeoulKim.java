package Programmers17to20;

public class SeoulKim {
    public String solution(String[] seoul) {
        String answer = "";
        for(int i=0; i<seoul.length; i++){
            //seoul[i] == "Kim";
            //숫자형들은 주소 값이 없기 떄문에 ==연산자로 비교가능
            //나머지는 각각의 주소 값이 있기 때문에 equals()매소드로 비교
            if(seoul[i].equals("Kim")){
                answer = "김서방은 "+ i +"에 있다";
            }
        }
        return answer;
    }
}
