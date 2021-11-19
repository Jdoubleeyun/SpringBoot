package Programmers29to32;

public class MinSquare {
    public int solution(int[][] sizes) {
        int answer = 0;
        int swit =0;
        for(int i=0; i < sizes.length; i++){
            if(sizes[i][0] < sizes[i][1]){
                swit = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = swit;
            }
        }
        int max1 =0;
        for(int i=1; i < sizes.length; i++){
            if(sizes[max1][0] < sizes[i][0]){
                max1 = i;
            }
        }
        int max2 =0;
        for(int i=1; i < sizes.length; i++){
            if(sizes[max2][1] < sizes[i][1]){
                max2 = i;
            }
        }
        answer = sizes[max1][0] * sizes[max2][1];
        return answer;
    }
}
