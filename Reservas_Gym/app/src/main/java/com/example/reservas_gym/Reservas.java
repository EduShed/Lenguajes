package com.example.reservas_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;
import java.util.Timer;

public class Reservas extends AppCompatActivity {
    private ImageView tipsPanel;
    private ImageView logo;
    private ImageView bottom;
    private TextView txtTip;
    private FadingTextView txtTipView;
    private TextView txtName;
    private String[] userData;
    private CountDownTimer myTimer;
    private ViewFlipper myVF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        Bundle recup = getIntent().getExtras();
        userData = recup.getStringArray("userData");
        animations();
    }

    private void connect()
    {
        tipsPanel = findViewById(R.id.imgTip);
        logo = findViewById(R.id.imgLogo3);
        txtTip = findViewById(R.id.txt_tips);
        txtTipView = findViewById(R.id.txtTipText);
        bottom = findViewById(R.id.imgbottom);
        txtName = findViewById(R.id.txtName);
    }

    /*
    Método que anima las transiciones de la pantalla tips
     */
    private void animations()
    {
        tipsPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipsPanel.animate().translationY(-2200).setDuration(1000).setStartDelay(0);
                bottom.animate().translationY(2200).setDuration(1000).setStartDelay(0);
                logo.animate().translationY(-800).setStartDelay(500);
                txtTip.animate().alpha(0).setDuration(100).setStartDelay(390);
                txtTipView.animate().alpha(0).setDuration(100).setStartDelay(390);
                myTimer = new CountDownTimer(850, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        launchReservas();
                        finish();
                    }
                }.start();
            }
        });
    }

    /*
    Intent que lanza este activity
     */
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Reservas.class);
    }

    /*
    Intent que lanza el menú de reservas y se le pasa los datos del usuario al otro activity
     */
    public void launchReservas()
    {
        Intent intent = ReservaActivity.launcheME(Reservas.this);
        intent.putExtra("userData", userData);
        startActivity(intent);
    }

}
