package com.example.reservas_gym;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    String tbEstudiantes = "Create Table Estudiantes (Cedula Text primary key, Nombre Text, Apellido Text, Carrera Text, Password Text)";
    String tbReservas = "Create Table Reservas (Id Integer primary key autoincrement, Id_Estudiante Integer, Fecha Text, Hora Text)";


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbEstudiantes);
        db.execSQL(tbReservas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table tbEstudiantes");
        db.execSQL("drop table tbReservas");
        db.execSQL(tbEstudiantes);
        db.execSQL(tbReservas);
    }
}
