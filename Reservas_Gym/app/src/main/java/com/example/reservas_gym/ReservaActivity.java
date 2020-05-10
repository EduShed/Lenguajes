package com.example.reservas_gym;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.spark.submitbutton.SubmitButton;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkButtonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class ReservaActivity extends AppCompatActivity {

    private CalendarView calendar;
    private TimePicker clock;
    private TextView phrase1, phrase2, phrase3, phrase4, phrase5, tvNombreEstudiante, tvCarreraEstudiante;
    private int daySelected, monthSelected, yearSelected, hourSelected, minuteSelected;
    private String[] userData;
    private ImageView tennisPlayer, taskComplete1, taskComplete2;
    private SparkButton btnReservar, btnVerReservas, btnSetReservaDate, btnSetReservaTime,  btnHacerReserva, btnCancel;
    SubmitButton  btnDateSelected, btnTimeSelected;
    private Button okDateWarningBtn, okTimeWarningBtn, okTimeWarningBtn2;
    private Calendar calendario = Calendar.getInstance();
    private boolean reservaButtonTouched, dateSelected;
    private boolean verReservasBtnTouched, timeSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        Bundle recup = getIntent().getExtras();
        userData = recup.getStringArray("userData");
        connect();
        tvNombreEstudiante.setText(userData[1] + " " + userData[2]);
        startingProcesses();
        ButtonListeners();
        /*
        En un textview pone la fecha que el usuario le dio tap
         */
       /** calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
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
        });**/
    }

    private void connect()
    {
       tennisPlayer = findViewById(R.id.imgTennisPlayer);
       phrase1 = findViewById(R.id.P1);
       phrase2 = findViewById(R.id.P2);
       phrase3 = findViewById(R.id.P3);
       phrase4 = findViewById(R.id.P4);
       phrase5 = findViewById(R.id.P5);
       btnReservar = findViewById(R.id.btnReservar);
       btnVerReservas = findViewById(R.id.btn_verReservas);
       btnHacerReserva = findViewById(R.id.btnHacerReserva);
       btnSetReservaTime = findViewById(R.id.btnClock);
       btnSetReservaDate = findViewById(R.id.btnCalendar);
       btnCancel = findViewById(R.id.btnCancel);
       taskComplete1 = findViewById(R.id.completed1);
       taskComplete2 = findViewById(R.id.completed2);
       tvNombreEstudiante = findViewById(R.id.txtNombreEstudiante);
        tvCarreraEstudiante = findViewById(R.id.txtCarreraEstudiante);
    }
    private void startingProcesses()
    {
        btnCancel.setEnabled(false);
        btnCancel.setClickable(false);
        btnHacerReserva.setEnabled(false);
        btnHacerReserva.setClickable(false);
        btnSetReservaTime.setEnabled(false);
        btnSetReservaTime.setClickable(false);
        btnSetReservaDate.setEnabled(false);
        btnSetReservaDate.setClickable(false);
        tvNombreEstudiante.setText(userData[1] + " " + userData[2]);
        tvCarreraEstudiante.setText(userData[3]);

        //Animations.
        phrase1.animate().setStartDelay(700).setDuration(1000).alpha(1);
        phrase2.animate().setStartDelay(2000).setDuration(800).alpha(1);
        phrase3.animate().setStartDelay(3700).setDuration(2000).alpha(1);
        phrase4.animate().setStartDelay(5500).setDuration(800).alpha(1);
        phrase5.animate().setStartDelay(6500).setDuration(800).alpha(1);
    }
    private void animations(int which)
    {
        //reservar buttons animations.
        if(which == 1)
        {
            if(!reservaButtonTouched)
            {
                //tennisPlayer.animate().setDuration(300).alpha(0);
                phrase1.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase2.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase3.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase4.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase5.animate().setStartDelay(0).setDuration(300).alpha(0);
                taskComplete1.animate().setStartDelay(100).setDuration(300).alpha(1);
                taskComplete2.animate().setStartDelay(100).setDuration(300).alpha(1);
                btnSetReservaTime.animate().setStartDelay(100).setDuration(300).alpha(1);
                btnSetReservaDate.animate().setStartDelay(100).setDuration(300).alpha(1);
                btnHacerReserva.animate().setStartDelay(100).setDuration(300).alpha(1);
                btnCancel.animate().setStartDelay(0).setDuration(300).alpha(1);
            }
        }
        //ver reservas button animations
         else if(which == 2)
        {
            if(!verReservasBtnTouched)
            {
                //tennisPlayer.animate().setDuration(800).alpha(0);
                phrase1.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase2.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase3.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase4.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase5.animate().setStartDelay(0).setDuration(300).alpha(0);
                verReservasBtnTouched = true;
            }
        }

         else if(which == 3)
        {
            //tennisPlayer.animate().setDuration(800).alpha(1);
            phrase1.animate().setStartDelay(0).setDuration(300).alpha(1);
            phrase2.animate().setStartDelay(0).setDuration(300).alpha(1);
            phrase3.animate().setStartDelay(0).setDuration(300).alpha(1);
            phrase4.animate().setStartDelay(0).setDuration(300).alpha(1);
            phrase5.animate().setStartDelay(0).setDuration(300).alpha(1);
            taskComplete1.animate().setStartDelay(0).setDuration(300).alpha(0);
            taskComplete2.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnSetReservaTime.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnSetReservaDate.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnHacerReserva.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnCancel.animate().setStartDelay(0).setDuration(300).alpha(0);
        }
    }
    public static  Intent launcheME(Context ctx)
    {
        return new Intent(ctx, ReservaActivity.class);
    }
    private void ButtonListeners()
    {
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(1);
                btnCancel.setEnabled(true);
                btnCancel.setClickable(true);
                btnSetReservaTime.setEnabled(true);
                btnSetReservaTime.setClickable(true);
                btnSetReservaDate.setEnabled(true);
                btnSetReservaDate.setClickable(true);
                reservaButtonTouched = true;

            }
        });
        btnHacerReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dateSelected && timeSelected)
                {
                    btnHacerReserva.setActiveImage(R.drawable.reserva_exitosa_button);
                    btnHacerReserva.setInactiveImage(R.drawable.reserva_exitosa_button);
                    //EL CODIGO PARA RESERVAR E INGRESAR LA RESERVA EN LA BASE DE DATOS AQUI
                }
                else
                {
                    //Yo hago un cosos para esto ahora
                }

            }
        });
        btnVerReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(1);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verReservasBtnTouched || reservaButtonTouched)
                {
                    animations(3);
                    taskComplete1.setImageResource(R.drawable.task_not_completed);
                    taskComplete2.setImageResource(R.drawable.task_not_completed);
                    btnCancel.setEnabled(false);
                    btnCancel.setClickable(false);
                    btnSetReservaTime.setEnabled(false);
                    btnSetReservaTime.setClickable(false);
                    btnSetReservaDate.setEnabled(false);
                    btnSetReservaDate.setClickable(false);
                    btnHacerReserva.setEnabled(false);
                    btnHacerReserva.setClickable(false);
                    verReservasBtnTouched = false;
                    reservaButtonTouched = false;
                    hourSelected = 0;
                    minuteSelected = -1;
                    monthSelected = 0;
                    daySelected = 0;
                    yearSelected = 0;
                    timeSelected = false;
                    dateSelected = false;
                }
            }
        });
        btnSetReservaDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daySelected = 0;
                monthSelected = 0;
                yearSelected = 0;
                taskComplete1.setImageResource(R.drawable.task_not_completed);
                makeDialogs(1);
            }
        });
        btnSetReservaTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hourSelected = 0;
                minuteSelected = -1;
                taskComplete2.setImageResource(R.drawable.task_not_completed);
                makeDialogs(2);
            }
        });
    }
    private void makeDialogs(int whichOne)
    {

        if(whichOne == 1)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.my_calendar_layout);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            btnDateSelected = dialog.findViewById(R.id.btnDateSetComplete);
            calendar = dialog.findViewById(R.id.myDatePicker);
            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    daySelected = dayOfMonth;
                    monthSelected = (month + 1);
                    yearSelected = year;
                }
            });
            btnDateSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyDate(daySelected, monthSelected, yearSelected, dialog);
                }
            });
            dialog.show();
        }
        else if(whichOne == 2)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.my_clock_layout);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            btnTimeSelected = dialog.findViewById(R.id.btnTimeSetComplete);
            clock = dialog.findViewById(R.id.myTimePicker);
            clock.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    minuteSelected = minute;
                    hourSelected = hourOfDay;
                }
            });
            btnTimeSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyTime(hourSelected, minuteSelected, dialog);
                }
            });
            dialog.show();
        }

        else if(whichOne == 3)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.date_warning);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            okDateWarningBtn = dialog.findViewById(R.id.OkDateWarningBtn);
            okDateWarningBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    makeDialogs(1);
                }
            });
            dialog.show();
        }

        //error dialog for hour passed the gym attention time
        else if(whichOne == 4)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.time_warning);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            okTimeWarningBtn = dialog.findViewById(R.id.OkTimeWarningBtn);
            okTimeWarningBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    makeDialogs(2);
                }
            });
            dialog.show();
        }

        //error message for hour not being pair
        else if(whichOne == 5)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.time2_warning);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            okTimeWarningBtn2 = dialog.findViewById(R.id.OkTimeWarningBtn2);
            okTimeWarningBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    makeDialogs(2);
                }
            });
            dialog.show();
        }
    }
    /*
   Verifica que la fecha que eligió el usuario sea la correcta (Un día después del día actual)
    */
    private void verifyDate(int daySelected, int monthSelected, int yearSelected, Dialog currentDialog)
    {
        CountDownTimer myTimer;
        final Dialog mycurrentDialog = currentDialog;
        int actualDay = calendario.get(Calendar.DAY_OF_MONTH);
        int actualMonth = calendario.get(Calendar.MONTH) + 1;
        int actualYear = calendario.get(Calendar.YEAR);
        String date = actualDay + " " + actualMonth + " " + actualYear;
        if(yearSelected == actualYear && monthSelected == actualMonth && daySelected == (actualDay+1))
        {
            myTimer = new CountDownTimer(1200, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    mycurrentDialog.dismiss();
                    dateSelected = true;
                    if(timeSelected)
                    {
                        btnHacerReserva.setEnabled(true);
                        btnHacerReserva.setClickable(true);
                    }
                    else
                    {
                        btnHacerReserva.setEnabled(false);
                        btnHacerReserva.setClickable(false);
                    }
                    taskComplete1.setImageResource(R.drawable.task_completed);
                }
            }.start();

        }
        else
        {
            currentDialog.dismiss();
            makeDialogs(3);
        }

    }
    private void verifyTime(int hourSelected, int minuteSelected, Dialog mycurrentDialog)
    {
        final Dialog myCurrentDialog = mycurrentDialog;
        if(hourSelected % 2 == 0 && hourSelected <= 18 && hourSelected >= 8 && minuteSelected == 0)
        {
            CountDownTimer myTimer;
            myTimer = new CountDownTimer(1200, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    myCurrentDialog.dismiss();
                    timeSelected = true;
                    if(dateSelected)
                    {
                        btnHacerReserva.setEnabled(true);
                        btnHacerReserva.setClickable(true);
                    }
                    else
                    {
                        btnHacerReserva.setEnabled(false);
                        btnHacerReserva.setClickable(false);
                    }
                    taskComplete2.setImageResource(R.drawable.task_completed);
                }
            }.start();
        }
        else if(hourSelected > 18)
        {
            mycurrentDialog.dismiss();
            makeDialogs(4);
        }
        else if(hourSelected < 8)
        {
            mycurrentDialog.dismiss();
            makeDialogs(4);
        }
        else if(minuteSelected != 0)
        {
            mycurrentDialog.dismiss();
            makeDialogs(5);
        }
        else if(hourSelected % 2 != 0)
        {
            mycurrentDialog.dismiss();
            makeDialogs(5);
        }
    }
}
