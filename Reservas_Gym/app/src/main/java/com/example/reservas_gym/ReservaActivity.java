package com.example.reservas_gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class ReservaActivity extends AppCompatActivity {

    CalendarView calendar;
    Button btnShowDate;
    TextView txtDate;
    TextView time;
    String date;
    RadioGroup rdTimes;
    RadioButton selectedRd;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {

                date = day + "/" + (month + 1) + "/" + year;
                txtDate.setText(date);
            }
        });
        rdTimes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id = rdTimes.getCheckedRadioButtonId();
                selectedRd = findViewById(id);
                time.setText("Hora: " + selectedRd.getText());
            }
        });
    }

    private void connect()
    {
        calendar = findViewById(R.id.calendar);
        btnShowDate = findViewById(R.id.btn_ShowDate);
        txtDate = findViewById(R.id.txtDate);
        rdTimes = findViewById(R.id.rdTimes);
        time = findViewById(R.id.txtTime);
    }
    public static  Intent launcheME(Context ctx)
    {
        return new Intent(ctx, ReservaActivity.class);
    }

}
