package com.example.reservas_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnLaunch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        btnLaunch = findViewById(R.id.btnLaunchNext);
        btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchNext();
            }
        });
    }
    public void LaunchNext()
    {
        ReservaActivity reserva = new ReservaActivity();
        Intent intent;
        intent = reserva.launcheME(MainActivity.this);
        startActivity(intent);
    }
}
