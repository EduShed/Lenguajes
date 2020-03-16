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

    static final ArrayList<Estudiante> students = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
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
                if(verifyId())
                {
                    Toast.makeText(getApplicationContext(), "El estudiante ya se encuentra en el sistema", Toast.LENGTH_LONG).show();
                }
                else
                {
                    students.add(new Estudiante(txtNom.getText().toString(), txtApe.getText().toString(), txtId.getText().toString(), txtPass.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    Intent intent = Login.launcheME(SignUp.this);
                    startActivity(intent);
                }

            }
        });
    }

    public boolean verifyId()
    {
        boolean found = false;
        for (Estudiante s:students)
        {
            if(txtId.getText().toString().equals(s.getId()))
            {
                found = true;
                break;
            }
        }
        return found;
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
}
