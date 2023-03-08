package com.example.puzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class game5 extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[][] buttons;
    private ImageButton aux;
    TextView tvEstado;
    TextView btDesarmar, btVolver;

    private TextView cronometroTextView;
    private CountDownTimer cronometro;

    private long tiempoRestante = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game5);

        // Initialize buttons array
        buttons = new ImageButton [3][3];

        buttons[0][0] = findViewById(R.id.btA);
        buttons[0][1] = findViewById(R.id.btB);
        buttons[0][2] = findViewById(R.id.btC);
        buttons[1][0] = findViewById(R.id.btD);
        buttons[1][1] = findViewById(R.id.btE);
        buttons[1][2] = findViewById(R.id.btF);
        buttons[2][0] = findViewById(R.id.btG);
        buttons[2][1] = findViewById(R.id.btH);
        buttons[2][2] = findViewById(R.id.btX);

        tvEstado=findViewById(R.id.tvEstado);
        btDesarmar=findViewById(R.id.btDesarmar);
        btVolver=findViewById(R.id.btVolver);

        cronometroTextView = findViewById(R.id.cronometro2);

        // Set onClickListener for each button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(this);
            }
        }


        int[][] posiciones = {{0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}};

        for (int i = 0; i < 100; i++) {
            int pos1 = (int) (Math.random() * 9);
            int pos2 = (int) (Math.random() * 9);

            ImageButton b1 = buttons[posiciones[pos1][0]][posiciones[pos1][1]];
            ImageButton b2 = buttons[posiciones[pos2][0]][posiciones[pos2][1]];

            swap(b1, b2);

            int[] temp = posiciones[pos1];
            posiciones[pos1] = posiciones[pos2];
            posiciones[pos2] = temp;
        }

        btDesarmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[][] posiciones = {{0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}};

                for (int i = 0; i < 100; i++) {
                    int pos1 = (int) (Math.random() * 9);
                    int pos2 = (int) (Math.random() * 9);

                    ImageButton b1 = buttons[posiciones[pos1][0]][posiciones[pos1][1]];
                    ImageButton b2 = buttons[posiciones[pos2][0]][posiciones[pos2][1]];

                    swap(b1, b2);

                    int[] temp = posiciones[pos1];
                    posiciones[pos1] = posiciones[pos2];
                    posiciones[pos2] = temp;

                    if (cronometro != null) {
                        cronometro.cancel();
                    }

                    iniciarCronometro();
                }

                updateEstado();
            }
        });

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        alertaInstrucciones();
        updateEstado();

    }

    void alertaInstrucciones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INSTRUCCIONES");

        // Crear un LinearLayout para el mensaje y la imagen
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Crear un TextView para el mensaje y establecer la gravedad del texto en el centro
        TextView message = new TextView(this);
        message.setText("Para resolver este rompecabezas, necesitas reproducir el patrón que se muestra en la siguiente imagen. Para desplazar cada ficha, simplemente presiónala y se moverá hacia el espacio libre. Si no hay un espacio libre adyacente a la ficha que deseas mover, no podrás mover esa ficha.\n");
        message.setGravity(Gravity.CENTER);

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
                iniciarCronometro();
                dialog.dismiss();
            }
        });

        builder.show();
    }




    public void onClick(View v) {
        ImageButton button = (ImageButton) v;

        // Find the button's position in the grid
        int row = -1, col = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == button) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        ImageButton up = row > 0 ? buttons[row - 1][col] : null;
        ImageButton down = row < 2 ? buttons[row + 1][col] : null;
        ImageButton left = col > 0 ? buttons[row][col - 1] : null;
        ImageButton right = col < 2 ? buttons[row][col + 1] : null;

        // Swap with the empty space if adjacent
        if (up != null && up.getTag().equals("X")) {
            swap(button, up);
        } else if (down != null && down.getTag().equals("X")) {
            swap(button, down);
        } else if (left != null && left.getTag().equals("X")) {
            swap(button, left);
        } else if (right != null && right.getTag().equals("X")) {
            swap(button, right);
        }

        updateEstado();

    }

    private String swap(ImageButton b1, ImageButton b2) {
        // Swap text and background between two buttons
        Drawable b1Bg = b1.getDrawable();
        String b1Text = b1.getTag().toString();
        b1.setImageDrawable(b2.getDrawable());
        b1.setTag(b2.getTag());

        b2.setImageDrawable(b1Bg);
        b2.setTag(b1Text);

        return "Intercambiar " + b1Text + " y " + b2.getTag() + "\n";
    }


    private void updateEstado() {
        boolean resuelto = false;
        String estado = "";

        if(buttons[0][0].getTag().equals("A") &&
                buttons[0][1].getTag().equals("B") &&
                buttons[0][2].getTag().equals("C") &&
                buttons[1][0].getTag().equals("D") &&
                buttons[1][1].getTag().equals("E") &&
                buttons[1][2].getTag().equals("F") &&
                buttons[2][0].getTag().equals("G") &&
                buttons[2][1].getTag().equals("H") &&
                buttons[2][2].getTag().equals("X")
        ){
            resuelto=true;

        }




        if (resuelto) {
            estado = "Resuelto";
            alertaVictoria1();

        } else {
            estado = "Desarmado";
        }
        tvEstado.setText(estado);
    }

    void alertaVictoria1(){
        AlertDialog.Builder ale1 = new AlertDialog.Builder(this);
        ale1.setTitle("Felicidades");
        ale1.setMessage("Haz resuelto el puzzle en "+timeresolved(obtenerTiempoActual())+" y ganaste 9 puntos!");
        ale1.setCancelable(false);//ventana modal
        ale1.setPositiveButton("Volver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.user1.RegistroPuntos(9);
                MenuGames.updatePuntos();
                dialog.dismiss();
                finish();
            }
        });
        ale1.show();
    }

    private void iniciarCronometro() {
        cronometro = new CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Convertir millisUntilFinished en minutos y segundos
                int minutos = (int) (millisUntilFinished / 1000) / 60;
                int segundos = (int) (millisUntilFinished / 1000) % 60;

                // Actualizar el TextView del cronómetro
                cronometroTextView.setText(String.format("%02d:%02d", minutos, segundos));

                // Almacenar el tiempo restante
                tiempoRestante = millisUntilFinished;
            }

            public void onFinish() {
                // Acciones a realizar cuando el cronómetro llega a cero
                cronometroTextView.setText("00:00");
                alertaDerrota();
            }
        }.start();
    }





    void alertaDerrota(){
        AlertDialog.Builder ale1 = new AlertDialog.Builder(this);
        ale1.setTitle("Perdiste");
        ale1.setMessage("No lograste resolver el Puzzle en el tiempo indicado");
        ale1.setCancelable(false);//ventana modal
        ale1.setPositiveButton("Volver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        ale1.show();
    }

    private String obtenerTiempoActual() {
        int minutos = (int) (tiempoRestante / 1000) / 60;
        int segundos = (int) (tiempoRestante / 1000) % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }


    String timeresolved(String a){
        cronometro.cancel();
        tiempoRestante = tiempoRestante;
        String b[]=a.split(":");
        int sec=300-(Integer.parseInt(b[0])*60+Integer.parseInt(b[1]));
        int min = sec/60;
        sec=sec-min*60;
        return min+":"+sec;
    }

}
