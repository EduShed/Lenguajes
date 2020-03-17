package com.example.reservas_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;

public class Reservas extends AppCompatActivity {
ImageView tipsPanel;
ImageView logo;
ImageView bottom;
ImageButton imgAgenda;
TextView txtTip;
FadingTextView txtTipView;
TextView txtName;
TextView reservatxt;
String[] userData = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        animations();
        launchSignUp();
        Bundle recup = getIntent().getExtras();
        userData = recup.getStringArray("userData");
    }

    private void connect()
    {
        tipsPanel = findViewById(R.id.imgTip);
        logo = findViewById(R.id.imgLogo3);
        txtTip = findViewById(R.id.txt_tips);
        txtTipView = findViewById(R.id.txtTipText);
        bottom = findViewById(R.id.imgbottom);
        txtName = findViewById(R.id.txtName);
        imgAgenda = findViewById(R.id.imgBtn_Agenda);
        reservatxt = findViewById(R.id.txt_reserva);
    }
    private void animations()
    {
        tipsPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipsPanel.animate().translationY(-1500).setDuration(800).setStartDelay(0);
                bottom.animate().translationY(1600).setDuration(800).setStartDelay(0);
                logo.animate().translationY(-212).setStartDelay(500);
                txtTip.animate().alpha(0).setDuration(300).setStartDelay(390);
                txtTipView.animate().alpha(0).setDuration(300).setStartDelay(390);
                txtName.animate().alpha(1).setDuration(300).setStartDelay(300);
                imgAgenda.animate().alpha(1).setDuration(400).setStartDelay(400);
                reservatxt.animate().alpha(1).setDuration(400).setStartDelay(400);
            }
        });
    }
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Reservas.class);
    }
    public void launchSignUp()
    {
        imgAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ReservaActivity.launcheME(Reservas.this);
                intent.putExtra("userData", userData);
                startActivity(intent);
            }
        });

    }
    private void darConsejo()
    {

    }

}
