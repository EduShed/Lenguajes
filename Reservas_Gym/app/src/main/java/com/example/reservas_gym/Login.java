package com.example.reservas_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;


import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private VideoView udemBg;
    MediaPlayer player;
    int playerVidPos;
    Button btnSignUp, btnLogin;
    ArrayList<Estudiante> listaUsuarios = new ArrayList<>();
    Archivos archivos;
    EditText txtId, txtPass;
    ImageButton admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        setVideo();
        launchSignUp();
        verifyUser();
        archivos = new Archivos(getApplicationContext(), "accounts.txt");
        listaUsuarios = getListaUsuarios();
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAdminPanel();
            }
        });
    }

    public void launchAdminPanel()
    {
        Intent intent = Admin_Activity.launcheME(Login.this);
        startActivity(intent);
    }

    public void connect()
    {
        udemBg = findViewById(R.id.video_bg);
        btnSignUp = findViewById(R.id.btnNew_account);
        btnLogin = findViewById(R.id.btnLogin);
        txtId = findViewById(R.id.txtCedula);
        txtPass = findViewById(R.id.txtContraseña);
        admin = findViewById(R.id.imgAdmin);
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
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Login.class);
    }

    public void launchSignUp()
    {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SignUp.launcheME(Login.this);
                startActivity(intent);
            }
        });

    }

    private void launchReserva(){
        Intent intent = Reservas.launcheME(Login.this);
        startActivity(intent);
    }

    private void verifyUser(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtId.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();
                if(id.isEmpty() || pass.isEmpty()){
                    txtId.setText("");
                    txtPass.setText("");
                    Toast.makeText(Login.this, "Debe llenar todos los campos obligatoriamente", Toast.LENGTH_SHORT).show();
                }else{
                   /** if(encontroUsuarioYContraseña(id, pass)){
                        txtId.setText("");
                        txtPass.setText("");
                        Toast.makeText(Login.this, "Logueado exitosamente", Toast.LENGTH_SHORT).show();
                        launchReserva();
                    }else{
                        txtId.setText("");
                        txtPass.setText("");
                        Toast.makeText(Login.this, "Cédula o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }**/
                   launchReserva();
                }
            }
        });
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

    private boolean encontroUsuarioYContraseña(String id, String pass){
        for(int i = 0; i < listaUsuarios.size(); i++){
            if(listaUsuarios.get(i).getId().equals(id.trim())){
                if(listaUsuarios.get(i).getPass().equals(pass.trim())){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    private ArrayList<Estudiante> getListaUsuarios(){
        return archivos.listaUsuarios();
    }
}
