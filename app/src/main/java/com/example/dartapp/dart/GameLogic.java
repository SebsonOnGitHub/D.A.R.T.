package com.example.dartapp.dart;

public class GameLogic {
    private int points_1_1, remaining_1_1, points_1_2, remaining_1_2;
    private int points_2_1, remaining_2_1, points_2_2, remaining_2_2;
    private int points_curr_tot, remaining_curr, round_1, round_2, round_tot, curr_player;
    private int points_curr[] = new int[3];
    public int i;

    public GameLogic() {
        points_1_1 = 0;
        remaining_1_1 = 0;
        points_1_2 = 0;
        remaining_1_2 = 501;

        points_2_1 = 0;
        remaining_2_1 = 0;
        points_2_2 = 0;
        remaining_2_2 = 501;

        points_curr[0] = 0;
        points_curr[1] = 0;
        points_curr[2] = 0;

        points_curr_tot = 0;
        remaining_curr = 0;

        round_1 = 0;
        round_2 = 0;
        round_tot = 60;
        curr_player = 1;
    }

    public int[] update(int dart, int value, boolean firstTurn){
        int correction = 0;

        points_curr[dart - 1] = value;
        if(dart == 1) {
            points_curr[1] = -1; // Signals that view should be seen as empty
            points_curr[2] = -1;
        }
        switch (dart) {
            case 1:
                correction++; // Corrects tot for using -1
            case 2:
                correction++;
                break;
            default:
                break;
        }
        points_curr_tot = points_curr[0] + points_curr[1] + points_curr[2] + correction;

        switch (curr_player) {
            case 1:
                if(dart == 1 && !firstTurn){
                    points_1_1 = points_1_2;
                    remaining_1_1 = remaining_1_2;
                }

                remaining_curr = remaining_1_2 - value;
                round_1++;
                points_1_2 = points_curr_tot;
                remaining_1_2 = remaining_curr;
                if(dart == 3)
                    curr_player = 2;
                break;
            case 2:
                if(dart == 1 && !firstTurn){
                    points_2_1 = points_2_2;
                    remaining_2_1 = remaining_2_2;
                }

                remaining_curr = remaining_2_2 - value;
                round_2++;
                points_2_2 = points_curr_tot;
                remaining_2_2 = remaining_curr;
                if(dart == 3)
                    curr_player = 1;
                break;
            default:
                break;
        }

        int[] data = {points_1_1, remaining_1_1, points_1_2, remaining_1_2, points_2_1,
                remaining_2_1, points_2_2, remaining_2_2, points_curr[0], points_curr[1],
                points_curr[2], points_curr_tot, remaining_curr, round_1, round_2, round_tot
                , curr_player};

        return data;
    }
}
