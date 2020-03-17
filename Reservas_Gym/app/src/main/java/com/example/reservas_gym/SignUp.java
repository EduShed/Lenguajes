package com.example.reservas_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

    Button btnSignUp;
    EditText txtNom;
    EditText txtApe;
    EditText txtId;
    EditText txtPass;

    Archivos archivos;

    ArrayList<Estudiante> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        archivos = new Archivos(getApplicationContext(), "accounts.txt");
        users = getListaUsuarios();
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
        return new Intent(ctx, SignUp.class);
    }

    public void signUp()
    {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = txtNom.getText().toString().trim();
                String lastname = txtApe.getText().toString().trim();
                String id = txtId.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();

                if(name.isEmpty() || lastname.isEmpty() || id.isEmpty() || pass.isEmpty()){
                    Toast.makeText(SignUp.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if(verifyId(id))
                {
                    txtNom.setText("");
                    txtApe.setText("");
                    txtId.setText("");
                    txtPass.setText("");
                    Toast.makeText(getApplicationContext(), "El estudiante ya se encuentra en el sistema", Toast.LENGTH_LONG).show();
                }
                else
                {
                    txtNom.setText("");
                    txtApe.setText("");
                    txtId.setText("");
                    txtPass.setText("");

                    String text = name + "\n" + lastname + "\n" + id + "\n" + pass + "\n";

                    try {
                        archivos.escribir(text);
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                        finish();
                    }catch(Exception e){
                        Log.e("", e.getMessage());
                    }
                }

            }
        });
    }

    private boolean verifyId(String id){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId().equals(id.trim())){
                return true;
            }
        }
        return false;
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

    private ArrayList<Estudiante> getListaUsuarios(){
        return archivos.listaUsuarios();
    }
}
