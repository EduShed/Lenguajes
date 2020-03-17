package com.example.reservas_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Admin_Activity extends AppCompatActivity {

    ImageView top;
    ImageView logo;
    ImageView bottom;
    ImageButton viewReg;
    TextView txtAdmin;
    TextView ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        animate();
    }

    private void connect()
    {
        top = findViewById(R.id.imgTop);
        logo = findViewById(R.id.imgLogo4);
        bottom = findViewById(R.id.imgbtm);
        viewReg = findViewById(R.id.imgBtn_verReserva);
        txtAdmin = findViewById(R.id.txtadmin);
        ver = findViewById(R.id.txtVer);
    }
    private void animate()
    {
        top.animate().translationY(-1500).setDuration(600).setStartDelay(95);
        bottom.animate().translationY(1600).setDuration(600).setStartDelay(95);
        logo.animate().translationY(-212).setStartDelay(500);
        txtAdmin.animate().alpha(1).setDuration(300).setStartDelay(300);
        viewReg.animate().alpha(1).setDuration(400).setStartDelay(400);
        ver.animate().alpha(1).setDuration(400).setStartDelay(400);
    }

    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Admin_Activity.class);
    }
}
