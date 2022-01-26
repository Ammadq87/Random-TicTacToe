package com.example.randomtictactoe;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class GameScreen extends AppCompatActivity {

    // Initializes 3x3 game board.
    int board[][] = new int[3][3];
    // turn var determines which player goes next. Batman: 1, Joker: 2
    int turn = 0;
    // p1 and p2 var store points accumulated by each player. Displayed later on in a TextView.
    int p1 = 0;
    int p2 = 0;
    // bypass var identifies if the "flip_function" button has been clicked.
    int bypass = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
    }

    // "Flip_Function" method is the coin on the game screen. When flipped, it randomly determines which player goes.
    public void Flip_Function (View view) {
        ImageButton flip = (ImageButton) findViewById (R.id.flip);
        // When clicked, bypass value increases.
        bypass++;
        // Random number generator is implemented to randomly decide which player goes.
        int random_turn = (int) (Math.random() * 2);
        if (random_turn == 1) {
            turn = 1;
            // turn value is set to the output of random generator. Toast message appears indicating whose turn it is.
            flip.setImageResource(R.drawable.b);
            Toast.makeText(getApplicationContext(), "Batman's turn", Toast.LENGTH_SHORT).show();
        }
        else {
            turn = 2;
            flip.setImageResource(R.drawable.j);
            Toast.makeText(getApplicationContext(), "Joker's turn", Toast.LENGTH_SHORT).show();
        }
    }

    public void flip(ImageView i) {
        // Places the "Batman" piece into position.
        if (turn == 1)
            i.setImageResource(R.drawable.batface);

        // Places the "Joker" piece into position.
        else if (turn == 2)
            i.setImageResource(R.drawable.jokface);
    }

    // Win method used to determine which ways player can win. Determines win and draw.
    public void win() {
        TextView p1points = (TextView) findViewById(R.id.p1points);
        TextView p2points = (TextView) findViewById(R.id.p2points);

        // 3 values for winner represent possible outcomes. 1: Batman , 2: Joker, 3: Draw.
        int winner = 0;

        // Statements check board position for a possible outcome.
        if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != 0)
            winner = board[0][0];
        else if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != 0)
            winner = board[1][0];
        else if (board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != 0)
            winner = board[2][0];
        else if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != 0)
            winner = board[0][0];
        else if (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != 0)
            winner = board[0][1];
        else if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != 0)
            winner = board[1][2];
        else if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0)
            winner = board[1][1];
        else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0)
            winner = board[0][2];
        else if (board[0][0] != 0 && board[0][1] != 0 && board[0][2] != 0 &&
                board[1][0] != 0 && board[1][1] != 0 && board[1][2] != 0 &&
                board[2][0] != 0 && board[2][1] != 0 && board[2][2] != 0)
            winner = 3;

        // Determines game outcome.
        // Increases points for winning player and updates the screen with new score.
        // Calls reset method to reset game.
        if (winner == 1) {
            Toast.makeText(getApplicationContext(), "Batman wins", Toast.LENGTH_SHORT).show();
            p1++;
            p1points.setText(""+p1);
            reset();
        } else if (winner == 2) {
            Toast.makeText(getApplicationContext(), "Joker wins", Toast.LENGTH_SHORT).show();
            p2++;
            p2points.setText(""+p2);
            reset();
        } else if (winner == 3) {
            Toast.makeText(getApplicationContext(), "Cat's game", Toast.LENGTH_SHORT).show();
            reset();
        }
    }

    // Resets game pieces on grid back to a blank picture, resets coin image.
    public void reset(){
        ImageView a = (ImageView) findViewById(R.id.a);
        a.setImageResource(R.drawable.blank);
        ImageView b = (ImageView) findViewById(R.id.b);
        b.setImageResource(R.drawable.blank);
        ImageView c = (ImageView) findViewById(R.id.c);
        c.setImageResource(R.drawable.blank);
        ImageView d = (ImageView) findViewById(R.id.d);
        d.setImageResource(R.drawable.blank);
        ImageView e = (ImageView) findViewById(R.id.e);
        e.setImageResource(R.drawable.blank);
        ImageView f = (ImageView) findViewById(R.id.f);
        f.setImageResource(R.drawable.blank);
        ImageView g = (ImageView) findViewById(R.id.g);
        g.setImageResource(R.drawable.blank);
        ImageView h = (ImageView) findViewById(R.id.h);
        h.setImageResource(R.drawable.blank);
        ImageView i = (ImageView) findViewById(R.id.i);
        i.setImageResource(R.drawable.blank);
        ImageButton flip = (ImageButton) findViewById (R.id.flip);
        flip.setImageResource(R.drawable.coin);

        // Loops through grid and sets values of arrays to 0.
        for(int k=0; k<3; k++){
            for(int j=0; j<3; j++){
                board[k][j]=0;
            }
        }
        // Sets turn and bypass value back to 0, so players cannot play pieces without clicking the "flip_function" button.
        turn = 0;
        bypass = 0;
    }

    // From lines 143-295, are the functions for buttons on the frid.
    public void aClick(View view){
        // When the "flip_function" is clicked, bypass value increases, therefore allowing the player to place a piece.
        // Calls "win" method to check for win and sets bypass value to 0.
        // If "flip_function" is not clicked, pop-up reminds player to click the "flip_function" button.
        if(bypass > 0) {
            if (board[0][0] == 0) {
                ImageView i = (ImageView) findViewById(R.id.a);
                board[0][0] = turn;
                flip(i);
            } else {
                Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass = 0;
        }

        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();
    }

    public void bClick(View view){
        if (bypass > 0) {
            if (board[0][1] == 0) {
                ImageView i = (ImageView) findViewById(R.id.b);
                board[0][1] = turn;
                flip(i);
            } else {
                Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass = 0;
        }
        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();
    }

    public void cClick(View view){
        if (bypass > 0){
            if (board[0][2] == 0) {
                ImageView i = (ImageView) findViewById(R.id.c);
                board[0][2] = turn;
                flip(i);
            }
            else{
                Toast.makeText(getApplicationContext(),"Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass=0;
        }
        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();
    }

    public void dClick(View view){
        if (bypass > 0){
            if (board[1][0] == 0) {
                ImageView i = (ImageView) findViewById(R.id.d);
                board[1][0] = turn;
                flip(i);
            }
            else{
                Toast.makeText(getApplicationContext(),"Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass = 0;
        }
        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();

    }

    public void eClick(View view){
        if (bypass > 0){
            if (board[1][1] == 0) {
                ImageView i = (ImageView) findViewById(R.id.e);
                board[1][1] = turn;
                flip(i);
            }
            else{
                Toast.makeText(getApplicationContext(),"Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass = 0;
        }

        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();
    }

    public void fClick(View view){
        if (bypass > 0) {
            if (board[1][2] == 0) {
                ImageView i = (ImageView) findViewById(R.id.f);
                board[1][2] = turn;
                flip(i);
            } else {
                Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass = 0;
        }
        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();
    }

    public void gClick(View view){
        if (bypass>0) {
            if (board[2][0] == 0) {
                ImageView i = (ImageView) findViewById(R.id.g);
                board[2][0] = turn;
                flip(i);
            } else {
                Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass = 0;
        }
        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();
    }

    public void hClick(View view){
        if (bypass > 0) {
            if (board[2][1] == 0) {
                ImageView i = (ImageView) findViewById(R.id.h);
                board[2][1] = turn;
                flip(i);
            } else {
                Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass = 0;
        }
        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();
    }

    public void iClick(View view){
        if (bypass > 0) {
            if (board[2][2] == 0) {
                ImageView i = (ImageView) findViewById(R.id.i);
                board[2][2] = turn;
                flip(i);
            } else {
                Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
            }
            win();
            bypass = 0;
        }
        else if (bypass < 1)
            Toast.makeText(getApplicationContext(), "Click the 'Flip' button to place a piece", Toast.LENGTH_SHORT).show();
    }
}
