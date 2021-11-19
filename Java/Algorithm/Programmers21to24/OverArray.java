package Programmers21to24;

public class OverArray {
    public int[] solution(long n) {
//        int length = (int)(Math.log10(n)+1);
        String ns = Long.toString(n);
        int[] answer = new int[ns.length()];
        for(int i = 0; i<ns.length(); i++){
            answer[i] = (int)(n%10);
            n = (int)(n/10);
        }
        return answer;
    }
}
