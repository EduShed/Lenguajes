package com.example.reservas_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    private VideoView udemBg;
    MediaPlayer player;
    int playerVidPos;

    DbHelper helper;

    Button btnSignUp;
    EditText txtNom;
    EditText txtApe;
    EditText txtId;
    EditText txtPass;
    EditText txtProfession;

    ArrayList<Estudiante> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        helper = new DbHelper(getApplicationContext(), "BD", null, 1);
        initializeList();
        setVideo();
        signUp();
    }

    public void connect()
    {
        udemBg = findViewById(R.id.video_bg2);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtNom = findViewById(R.id.txtNombre);
        txtApe = findViewById(R.id.txtApellido);
        txtId = findViewById(R.id.txtID);
        txtPass = findViewById(R.id.txtPassword);
        txtProfession = findViewById(R.id.txtProfession);
    }


    public void setVideo()
    {
        Uri vidUri = Uri.parse("android.resource://" +getPackageName()+ "/" +R.raw.unidem);
        udemBg.setVideoURI(vidUri);
        udemBg.start();
        udemBg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                player = mp;
                player.setLooping(true);
                if(playerVidPos != 0)
                {
                    player.seekTo(playerVidPos);
                    player.start();
                }
            }
        });
    }

    /*
    Intent que lanza este activity
     */
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, SignUp.class);
    }

    public void signUp()
    {
        /*
        Verifica si existe un usuario con el id ingresado, si no existe entonces se escribe el nuevo usuario en el archivo plano, también verifica
        si los campos de los Edit Texts están vacíos
         */
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = txtNom.getText().toString().trim();
                String lastname = txtApe.getText().toString().trim();
                String profession = txtProfession.getText().toString().trim();
                String id = txtId.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();

                if(name.isEmpty() || lastname.isEmpty() || profession.isEmpty() || id.isEmpty() || pass.isEmpty()){
                    Toast.makeText(SignUp.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if(verifyId(id) || id.equals("123"))
                {
                    txtNom.setText("");
                    txtApe.setText("");
                    txtProfession.setText("");
                    txtId.setText("");
                    txtPass.setText("");
                    Toast.makeText(getApplicationContext(), "El estudiante ya se encuentra en el sistema", Toast.LENGTH_LONG).show();
                }
                else
                {
                    txtNom.setText("");
                    txtApe.setText("");
                    txtProfession.setText("");
                    txtId.setText("");
                    txtPass.setText("");

                    GuardarEstudiante(id, name, lastname, profession, pass);
                    finish();
                }

            }
        });
    }

    private void GuardarEstudiante(String cedula, String name, String lastname, String profession, String password){
        DbHelper helper = new DbHelper(this, "BD", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            String insert = "INSERT INTO Estudiantes(Cedula, Nombre, Apellido, Carrera, Password) VALUES('"+cedula+"', '"+name+"', '"+lastname+"', '"+profession+"', '"+password+"')";

            db.execSQL(insert);
            db.close();

            Toast.makeText(this, "Registrado exitosamente", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this, "Error: " + ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    /*
    Verifica si encuentra un Id igual al que se ingresa como parámetro
     */
    private boolean verifyId(String id){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equals(id.trim())){
                return true;
            }
        }
        return false;
    }

    private ArrayList<Estudiante> GetDBEstudiantes(){
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Select * from Estudiantes";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String cedula = c.getString(0);
                    String name = c.getString(1);
                    String lastname = c.getString(2);
                    String profession = c.getString(3);
                    String password = c.getString(4);
                    estudiantes.add(new Estudiante(name, lastname, profession, cedula, password));
                }while(c.moveToNext());
            }
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return estudiantes;
    }

    protected void onPause() {

        super.onPause();
        playerVidPos = player.getCurrentPosition();
        udemBg.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        udemBg.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }

    /*
    Asigna a la lista que está como variable global la lista que tiene los usuarios leídos en el archivo plano
     */
    private void initializeList(){
        users = GetDBEstudiantes();
    }
}
