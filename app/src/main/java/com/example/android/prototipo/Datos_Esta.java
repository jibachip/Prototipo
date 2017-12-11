package com.example.android.prototipo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datos_Esta extends AppCompatActivity {
    String nombre,direc,horario,precio,espdis;
    int s = 0;
    Date date = new Date();
    Button btnGen;       //variable para usar el EditText del activity
    ImageView image;     //variable para usar el Image del activity
    String textoDatos;   //variable que se usara para guardar los datos introducidos en el EditText del activity
    Bitmap bitmap;
    String hor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos__esta);
        TextView tv1 = findViewById(R.id.tvNombre);
        TextView tv2 = findViewById(R.id.tvDireccion);
        TextView tv3 = findViewById(R.id.tvHorario);
        TextView tv4 = findViewById(R.id.tvPrecio);
        TextView tv5 = findViewById(R.id.tvEsp);
        Button btn = findViewById(R.id.btnReserva);
        nombre = MapsActivity2.estac;
        direc = MapsActivity2.direc;
        horario = MapsActivity2.horaAC;
        precio = MapsActivity2.precio;
        espdis = MapsActivity2.disp;

        tv1.setText(nombre);
        tv2.setText(direc);
        tv3.setText(horario);
        tv4.setText(precio);
        tv5.setText("Espacios disponibles: " + espdis);
        image = (ImageView) findViewById(R.id.image);      //inicializamos la variable image con el ide del Image del activity

        SharedPreferences shre2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String previouslyEncodedImage = shre2.getString("image_data", "");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            image.setImageBitmap(bitmap);
        }


        if(espdis.equals("0")){
            btn.setEnabled(false);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(Datos_Esta.this,GenerarCodigo.class);
                startActivity(intent);*/
                //metodo para cuando den click en el boton
                DateFormat hora = new SimpleDateFormat("HH:mm:ss");  //formato para obtener la hora
                DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy"); //formato para obtener la fecha
                hor = (hora.format(date)).toString().trim();
                String fec = (fecha.format(date)).toString().trim();
                textoDatos = ","+hor+","+fec;;     //Guardamos lo que tiene el EditText en la variable textoDatos y quitamos los excesos con '.trim'
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();  //Este objeto se crea para codificar un codigo usando la configuracion predeterminada
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(textoDatos, BarcodeFormat.QR_CODE,200,200); //Para la representacion de una matriz 2D de bits donde:3
                    // se inisializa con el objeto para codificar el codigo mediante el formato 2D (texto a codificar, formato de codigo 'QR', valor de x, valor de y) El origen está en la parte superior izquierda.
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder(); //Ayuda a coidificar codigos en mapas de bits para poderlos mostrar
                    bitmap = barcodeEncoder.createBitmap(bitMatrix); // se crea un mapa de bits con el objeto barcodeEncoder que a su vez le mandamos el bitMatrix que es el que tiene el codigo del texto
                    image.setImageBitmap(bitmap); //mostramos en el Image el mapa de bits

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();

                    String encoded = Base64.encodeToString(b, Base64.DEFAULT);

                    SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor edit=shre.edit();
                    edit.putString("image_data",encoded);
                    edit.commit();

                }
                catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });

    }
    private String tiempo() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH-mm-­ss");
        String formato = df.format(c.getTime());
        return formato;
    }
}