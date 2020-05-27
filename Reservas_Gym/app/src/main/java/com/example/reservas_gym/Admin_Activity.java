package com.example.reservas_gym;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Admin_Activity extends AppCompatActivity {

    private ImageView cardVbG;
    private ViewPager myViewPager;
    private ViewPagerAdapter viewPagerAdapter;
    RelativeLayout myActivityBg;
    private List<Integer> studentIds = new ArrayList<>();
    private List<String> studentResTimes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();

       // rV.setHasFixedSize(true);
       // rVlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //rVadapter = new RecyclerViewAdapter(getInformationList());
        //rV.setItemAnimator(new DefaultItemAnimator());
       // rV.setLayoutManager(rVlayoutManager);
       // rV.setAdapter(rVadapter);

        viewPagerAdapter = new ViewPagerAdapter(sendInformationToPager(), this);
        myViewPager.setAdapter(viewPagerAdapter);
        myViewPager.setPadding(130, 0 , 130, 0);
        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0)
                {
                    animate(cardVbG, R.drawable.img1);
                }
                else if(position == 1)
                {
                    animate(cardVbG, R.drawable.img5);
                }
                else if(position == 2)
                {
                    animate(cardVbG, R.drawable.img3);
                }
                else if(position == 3)
                {
                    animate(cardVbG, R.drawable.img4);
                }
                else
                {
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void connect()
    {
        myViewPager = findViewById(R.id.myViewPager);
        myActivityBg = findViewById(R.id.admin_activity_layout);
        cardVbG =findViewById(R.id.CardviewBg);

    }

    private void getIdsAndTimes()
    {
        DbHelper myHelper = new DbHelper(Admin_Activity.this, "BD", null, 1);
        SQLiteDatabase myDB = myHelper.getReadableDatabase();
        String sqlCommand = "Select Id_Estudiante, Hora from Reservas";
        Cursor cursor = myDB.rawQuery(sqlCommand, null);
        String line = "";
        if(cursor.moveToFirst())
        {
            do
            {
                studentIds.add(cursor.getInt(0));
                studentResTimes.add(cursor.getString(1));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        myDB.close();
    }
    private List sendInformationToPager()
    {
        List<ViewPagerModel> myArray = new ArrayList<>();
        if(studentIds.size() != 0)
        {
            int counter= 0;
            getIdsAndTimes();
            DbHelper myHelper = new DbHelper(Admin_Activity.this, "BD", null, 1);
            SQLiteDatabase myDB = myHelper.getReadableDatabase();
            String sqlCommand = "Select Nombre, Apellido from Estudiantes where Cedula = '"+ studentIds.get(0) +"'";
            Cursor cursor = myDB.rawQuery(sqlCommand, null);
            String line = "";

            for(int i = 0; i < studentIds.size(); i++)
            {
                if(cursor.moveToFirst())
                {
                    myArray.add(new ViewPagerModel(R.drawable.profilepic2, cursor.getString(0) + " " + cursor.getString(1) + "\n" + "Id: " + studentIds.get(i), "\n Hora de reserva: " + studentResTimes.get(i)));
                    sqlCommand = "Select Nombre, Apellido from Reservas where Id_Estudiante = '"+ studentIds.get(i) + "'";
                }
            }
            cursor.close();
            myDB.close();
        }

        else
        {

        }

        return myArray;
    }
    private void animate(final ImageView imageView, final int img) {

        //imageView <-- The View which displays the images
        //images[] <-- Holds R references to the images to display
        //imageIndex <-- index of the first image to show in images[]
        //forever <-- If equals true then after the last image it starts all over again with the first image resulting in an infinite loop. You have been warned.

        int fadeInDuration = 500; // Configure time values here
        int timeBetween = 3000;
        int fadeOutDuration = Integer.MAX_VALUE;

        imageView.setVisibility(View.INVISIBLE);    //Visible or invisible by default - this will apply when the animation ends
        imageView.setImageResource(img);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
        fadeIn.setDuration(fadeInDuration);

        Animation fadeOut = new AlphaAnimation(1, 1);
        fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
        fadeOut.setStartOffset(fadeInDuration + timeBetween);
        fadeOut.setDuration(fadeOutDuration);

        AnimationSet animation = new AnimationSet(false); // change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        animation.setRepeatCount(1);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {

                animate(imageView, img);
                         //Calls itself to start the animation all over again in a loop if forever = true

            }
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
        });
    }
    /*
    Hacer animación en el admin activity
     */
    /*
    Método de la clase Reserva donde obtiene toda la información de la reserva
     */
    private String getReservas(ArrayList<Reserva> list)
    {
        String info= "";
        for(Reserva e:list)
        {
            int cont = 0;
            info += e.getReservationInfo();
            cont++;
            if(cont == 1){
                info += "\n\n";
            }
        }
        return info;
    }

    /*
    Se crea un Alert dialog para mostrar las reservas con un textview (configurado en reserva_view_layout.xml)
     */
    private void showReservas()
    {

    }

    /*
    Intent para lanzar el admin activity
     */
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Admin_Activity.class);
    }
}
