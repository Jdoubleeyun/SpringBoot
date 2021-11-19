package Programmers21to24;


public class Strange {
    public String solution(String s) {
        String answer;
        answer = s.toUpperCase();
        char[] chars = answer.toCharArray();

        //앞문자가 대문자라면 소문자로 치환
        for (int i = 1; i < chars.length; i++) {
            if (62 <= chars[i - 1] && chars[i - 1] <= 90) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        answer = String.valueOf(chars);
        return answer;
    }


//    public String solution(String s) {
//        String answer = "";
//        int val =0;
//        String[] str = s.split("");
//        for(int i = 0; i < str.length; i++){
//            if(str[i].equals(" ")){
//                val = 0;
//            }else{
//                if(val % 2 == 0){
//                    val++;
//                    str[i] = str[i].toUpperCase();
//                }else{
//                    val++;
//                    str[i] = str[i].toLowerCase();
//                }
//            }
//            answer += str[i];
//        }
//
//        return answer;
//    }
}
