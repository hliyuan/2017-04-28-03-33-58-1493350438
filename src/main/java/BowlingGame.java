public class BowlingGame {
    /**
     *思路：分别计算每格中两次投球的分数，放入一个数组里。
     *		如果为strick则此格分数为10+后面两次击球的分数
     *		如果为spare则此格的分数为10+后面一次击球的分数
     *		else，则此格分数为两次击球的分数之和
     *
     *
     * */
    public int getBowlingScore(String bowlingCode) {
        String[] arr = new String[11];
        String[] lastframe = bowlingCode.split("\\|\\|");
        if(lastframe.length==1)
            arr[10] = "";
        else arr[10] = lastframe[1];
        String[] preframe = lastframe[0].split("\\|");
        for (int i = 0; i < preframe.length ; i++) {
            arr[i]=preframe[i];
        }

        int sum = 0;
        for (int i = 0; i < arr.length-1 ; i++) {
            int[] score = getScoresPerFrame(arr[i]);
            if (score[0] == 10) {
                int[] next1score = getScoresPerFrame(arr[i+1]);
                if(i<9) {

                    if (next1score[0] == 10) {
                        int[] next2score = getScoresPerFrame(arr[i+2]);
                        sum += score[0] + next1score[0] + next2score[0];
                    } else if (next1score[1] == 10)  {
                        sum += score[0] + next1score[1];
                    }else{
                        sum += score[0] + next1score[0] + next1score[1];
                    }
                }else {
                    sum+= score[0]+next1score[0]+next1score[1];
                }
            }else if (score[1] == 10){
                int[] nextscore = getScoresPerFrame(arr[i+1]);
                sum += score[1] + nextscore[0];
            }else{
                sum += score[0]+score[1];
            }

        }

        return sum;
    }

    public int[] getScoresPerFrame(String target){
        int score[] = {0,0};
        if(target.equals("")||target.isEmpty()) return score;
        char [] arr = target.toCharArray();

        if (arr.length == 1) {
            if (arr[0]=='X') score[0] = 10;
            else score[0] = Integer.parseInt(arr[0]+"");
        }else{
            if (arr[0]=='X'&&arr[1]=='X'){
                score[0]=10;
                score[1]=10;
            }else {
                if (arr[0] == '-') score[0] = 0;
                else if (arr[0] != '-') score[0] = Integer.parseInt(arr[0]+"");

                if (arr[1] == '-') score[1] = 0;
                else if (arr[1] == '/') score[1] = 10;
                else score[1] = Integer.parseInt(arr[1]+"");
            }
        }

        return score;
    }


}
