package com.example.reservas_gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReservaActivity extends AppCompatActivity {

    CalendarView calendar;
    Button btnReserve;
    TextView txtDate;
    TextView time;
    String date, dayPicked, monthPicked, yearPicked, hourPicked;
    String[] userData = new String[3];
    RadioGroup rgTimes;
    RadioButton selectedRd, rb1, rb2, rb3, rb4, rb5, rb6;
    Archivos archivos;
    Calendar calendario = Calendar.getInstance();
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        Bundle recup = getIntent().getExtras();
        userData = recup.getStringArray("userData");
        archivos = new Archivos(getApplicationContext(), "reserves.txt");

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                dayPicked = day + "";
                monthPicked = (month + 1) + "";
                yearPicked = year + "";
                date = day + "/" + (month + 1) + "/" + year;
                txtDate.setText(date);
                rb1.setEnabled(true);
                rb2.setEnabled(true);
                rb3.setEnabled(true);
                rb4.setEnabled(true);
                rb5.setEnabled(true);
                rb6.setEnabled(true);
            }
        });
        rgTimes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id = rgTimes.getCheckedRadioButtonId();
                selectedRd = findViewById(id);
                hourPicked = selectedRd.getText().toString().trim();
                time.setText("Hora: " + hourPicked);
            }
        });
        doReserve();
    }

    private void doReserve(){
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyDate();
            }
        });
    }

    private void verifyDate(){
        String text = "";
        String datePicked = txtDate.getText().toString().trim();
        String timePicked = time.getText().toString().trim();
        String actualDay = calendario.get(Calendar.DAY_OF_MONTH) + 1 + "";
        String actualMonth = calendario.get(Calendar.MONTH) + 1 + "";
        String actualYear = calendario.get(Calendar.YEAR) + "";
        text = actualDay + "\n" + actualMonth + "\n" + actualYear + "\n" +  hourPicked + "\n"  + userData[0] + "\n" + userData[1] + "\n" + userData[2] + "\n";

        if(datePicked.isEmpty() || timePicked.isEmpty()){
            Toast.makeText(ReservaActivity.this, "Debes seleccionar obligatoriamente una fecha y una hora", Toast.LENGTH_SHORT).show();
        }else if(dayPicked.trim().equals(actualDay.trim()) && monthPicked.trim().equals(actualMonth.trim()) && yearPicked.trim().equals(actualYear.trim())){
            Toast.makeText(ReservaActivity.this, "Reserva hecha exitosamente!", Toast.LENGTH_SHORT).show();
            try {
                archivos.escribir(text);
            } catch (Exception e) {
                Log.e("", e.getMessage());
            }
            launchLogin();
            finish();
        }else{
            Toast.makeText(ReservaActivity.this, "Su reserva debe ser con un dia de anterioridad", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchLogin(){
        Intent intent = Login.launcheME(ReservaActivity.this);
        startActivity(intent);
    }

    private void connect()
    {
        calendar = findViewById(R.id.calendar);
        btnReserve = findViewById(R.id.btn_Reserve);
        txtDate = findViewById(R.id.txtDate);
        rgTimes = findViewById(R.id.rgTimes);
        time = findViewById(R.id.txtTime);
        rb1 = findViewById(R.id.rb_810);
        rb2 = findViewById(R.id.rb_1012);
        rb3 = findViewById(R.id.rb_1214);
        rb4 = findViewById(R.id.rb_1416);
        rb5 = findViewById(R.id.rb_1618);
        rb6 = findViewById(R.id.rb_1820);
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        rb5.setEnabled(false);
        rb6.setEnabled(false);
    }
    public static  Intent launcheME(Context ctx)
    {
        return new Intent(ctx, ReservaActivity.class);
    }

}
