package com.example.puzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuGames extends AppCompatActivity {
    TextView tvNombre;
    static TextView tvUserPoints;
    TextView game1;
    TextView game2;
    TextView game3;
    TextView game4;
    TextView game5;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_games);
        getSupportActionBar().hide();


        tvNombre=findViewById(R.id.tvUserName);
        tvUserPoints=findViewById(R.id.tvUserPoints);
        game1=findViewById(R.id.btGame1);
        game2=findViewById(R.id.btGame2);
        game3=findViewById(R.id.btGame3);
        game4=findViewById(R.id.btGame4);
        game5=findViewById(R.id.btGame5);

        tvNombre.setText("Bienvenido "+MainActivity.user1.getUser());
        updatePuntos();

        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), game1.class);
                startActivity(intent);
            }
        });

        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), game2.class);
                startActivity(intent);
            }
        });

        game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), game3.class);
                startActivity(intent);
            }
        });

        game4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), game4.class);
                startActivity(intent);
            }
        });

        game5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), game5.class);
                startActivity(intent);
            }
        });

    }

    static void updatePuntos(){
        tvUserPoints.setText("Puntos: "+MainActivity.user1.getPuntos()+"pts");
    }


}

