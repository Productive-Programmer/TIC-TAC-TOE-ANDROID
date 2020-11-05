package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //0:o 1:x 2:empty
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    Boolean gameActive = true;

    public void dropIn(View v){
        //Cuz we want to know which view was tapped on

        ImageView counter = (ImageView) v;
        counter.setTranslationY(-1000);
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive){
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0){
                counter.setImageResource(R.drawable.omega);
                activePlayer =1;
            }
            else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
               //SomeOneHasWon
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "O";
                    } else {
                        winner = "X";
                    }
                    Toast.makeText(this, winner + " has won!!", Toast.LENGTH_SHORT).show();
                    Button playAgain = findViewById(R.id.playAgainButton);
                    TextView tv = findViewById(R.id.textView);
                    tv.setText(winner +" has WOnnnn!!!");
                    playAgain.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);

                 }
            }
        }

    }
    public void playAgain(View v){
        Button playAgain = findViewById(R.id.playAgainButton);
        TextView tv = findViewById(R.id.textView);

        playAgain.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer = 0;
        for (int i = 0;i<gameState.length;i++){
            gameState[i] = 2;
        }

        Boolean gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}