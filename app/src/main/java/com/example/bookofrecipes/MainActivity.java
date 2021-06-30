package com.example.bookofrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addRec=findViewById(R.id.AddRec);
        Button recipe=findViewById(R.id.Recipe);
        Button exit=findViewById(R.id.Exit);

        addRec.setBackgroundColor(Color.LTGRAY);
        recipe.setBackgroundColor(Color.LTGRAY);
        exit.setBackgroundColor(Color.LTGRAY);

        addRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddRecipe.class);
                startActivity(intent);
            }
        });

        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Recipes.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}