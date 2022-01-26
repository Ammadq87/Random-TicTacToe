package com.example.randomtictactoe;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
public class ChooseCharacter extends AppCompatActivity {
    //Remembers if button is clicked
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_character);

        //Next button leads to "GameScreen" if characters are chosen for both players
        ImageButton next = (ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Counter must be greater than 1 to proceed to next screen
                if (counter < 2)
                    Toast.makeText(getApplicationContext(), "Please Choose A Character.", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(ChooseCharacter.this, GameScreen.class);
                    startActivity(intent);
                }
            }
        });
    }

    //Choosing Batman as your character
    //increases counter value when button is clicked
    public void bat (View view){
        ImageButton b = (ImageButton)findViewById(R.id.b);
        counter++;
        Toast.makeText(getApplicationContext(), "Player 1 chose Batman", Toast.LENGTH_SHORT).show();
    }

    //Choosing joker as your character
    public void joker (View view){
        ImageButton j = (ImageButton)findViewById(R.id.j);
        counter++;
        Toast.makeText(getApplicationContext(), "Player 2 chose The Joker", Toast.LENGTH_SHORT).show();
    }
}
