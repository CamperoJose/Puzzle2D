package com.example.puzzle;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username_input;
    TextView btIngresar;
    TextView About;

    public static User user1 = new User("");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //Para ocultar el Action bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username_input=findViewById(R.id.username_input);
        btIngresar=findViewById(R.id.btIngresar);
        About=findViewById(R.id.About);

        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username_input.getText().toString();
                if(name.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Ingrese su nombre", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();

                    toast.show();


                }else{
                    user1.SetName(name);
                    Intent intent = new Intent(getBaseContext(), MenuGames.class);
                    startActivity(intent);
                }
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AboutAlert();
            }
        });

    }

    void AboutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acerca de");

        // Crear un LinearLayout para el mensaje y la imagen
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Crear un TextView para el mensaje y establecer la gravedad del texto en el centro
        TextView message = new TextView(this);
        message.setText("\tDesarrollador: José Antonio Campero Morales\n\tMateria: Programación de dispositivos móviles");

        // Crear un ImageView y configurar la imagen que desea mostrar
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.hint5);

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        // Agregar el TextView y el ImageView al LinearLayout
        linearLayout.addView(message);
        linearLayout.addView(imageView);

        builder.setView(linearLayout);
        builder.setCancelable(false);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();


    }


    @Override
    public void onClick(View v) {

    }
}
