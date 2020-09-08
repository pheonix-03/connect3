package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:yellow 1:red 2:empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};//empty initialization

    int[][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};//winning positions logic

    int activePlayer=0;

    boolean gameActive=true;

    public void dropIn(View view){
        ImageView counter= (ImageView) view;//getting the id of the touched image view



        int tappedCounter= Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                activePlayer = 1;

                counter.setImageResource(R.drawable.yellow);


            } else {
                activePlayer = 0;

                counter.setImageResource(R.drawable.red);//changes the color


            }
            counter.animate().alpha(1).setDuration(1000);//animation

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //someone has won
                    gameActive=false;
                    String winner;
                    if (activePlayer == 1) {
                        winner = "Yellow";

                    } else {
                        winner = "Red";

                    }

                    Button playAgainButton =(Button) findViewById(R.id.playAgain);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnertextView);

                    winnerTextView.setText(winner + " has won");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgainButton =(Button) findViewById(R.id.playAgain);

        TextView winnerTextView = (TextView) findViewById(R.id.winnertextView);



        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++){

            ImageView counters=(ImageView) gridLayout.getChildAt(i);

            counters.setImageDrawable(null);

        }
        for(int i=0 ;i<9;i++){
            gameState[i]=2;
        }


        activePlayer=0;

        gameActive=true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}