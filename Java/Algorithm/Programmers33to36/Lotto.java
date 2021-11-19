package Programmers33to36;

public class Lotto {
    public int[] solution(int[] lottos, int[] win_nums) {
        int count  =0 ;
        int zero =0;
        for(int i=0; i<lottos.length; i++){
            if(lottos[i]==0)
                zero++;
            for(int j=0; j<win_nums.length; j++){
                if(win_nums[j] == lottos[i])
                    count ++;
            }
        }
        int min = count;
        int max = count + zero;

        int[] answer = {Math.min(7 - max, 6), Math.min(7 - min, 6)};
        return answer;
    }

}
