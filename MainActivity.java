package com.example.randomtictactoe;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // When play button is clicked, it leads to the "choose character" screen.
        ImageButton playbutton = (ImageButton)findViewById(R.id.playbutton);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseCharacter.class);
                startActivity(intent);
            }
        });

        // When instructions button is clicked, it leads to "instructions" screen.
        ImageButton instructionbutton = (ImageButton)findViewById(R.id.instructionsbutton);
        instructionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, instructions.class);
                startActivity(intent);
            }
        });
    }
}
