package Programmers13to16;

public class InstringPYcount {
    boolean solution(String s) {
        boolean answer = true;
        int Ycount =0;
        int Pcount =0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'y'||s.charAt(i) == 'Y'){
                Ycount++;
            }
            else if(s.charAt(i) == 'p'||s.charAt(i) == 'P'){
                Pcount++;
            }
        }
        if(Ycount != Pcount){
            answer = false;
        }else{answer = true;}

        System.out.println("Hello Java");

        return answer;
    }
}
