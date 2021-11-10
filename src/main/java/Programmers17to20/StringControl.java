package Programmers17to20;

public class StringControl {
    public boolean solution(String s) {
        boolean answer = true;
        char[] ch = new char[s.length()];
        if(s.length() == 4 || s.length() == 6){
            for(int i=0; i<s.length(); i++){
                ch[i] = s.charAt(i);
                if(ch[i] < 48 || ch[i] > 57){
                    answer = false;
                }
            }
        }else{answer = false;}
        return answer;
    }
}
