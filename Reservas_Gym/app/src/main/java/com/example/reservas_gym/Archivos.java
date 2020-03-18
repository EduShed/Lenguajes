package com.example.reservas_gym;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Archivos {
    String Archivo;
    Context ctx;
    FileOutputStream fos;
    FileInputStream fis;

    /*
     Constructor que recibe el contexto y el nombre del archivo que se va a abrir
     */
    public Archivos(Context ctx, String archivo) {
        this.ctx = ctx;
        this.Archivo = archivo;
    }

    public void escribir(String textArchivo) throws IOException {
        try {
            fos = ctx.openFileOutput(Archivo, Context.MODE_APPEND);
            fos.write(textArchivo.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("", e.getMessage());
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
    }

    public ArrayList<Reserva> listaReservas(){
        ArrayList<Reserva> lista = new ArrayList<>();
        String lectura = "";
        String hour = "";
        String name = "";
        String lastname = "";
        String id = "";
        String date = "";
        try {
            int cont = 0;
            fis = ctx.openFileInput(Archivo);
            int i = 2;
            char caracter;
            while (i > 0) {
                i = fis.read();
                caracter = (char) i;
                lectura += caracter;
                if (i == '\n') {
                    if(cont == 0){
                        date = lectura.trim() + "/";
                    }else if(cont == 1){
                        date += lectura.trim() + "/";
                    }else if(cont == 2) {
                        date += lectura.trim();
                    }else if(cont == 3){
                        hour = lectura.trim();
                    }else if(cont == 4){
                        id = lectura.trim();
                    }else if(cont == 5){
                        name = lectura.trim();
                    }
                    else if (cont == 6)
                    {
                        lastname = lectura.trim();
                        lista.add(new Reserva(name, lastname, id, date, hour));
                        cont =-1;
                    }
                    lectura = "";
                    cont++;
                }
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return lista;
    }

    public ArrayList<Estudiante> listaUsuarios() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        String lectura = "";
        String name = "";
        String lastname = "";
        String id = "";
        String pass = "";
        try {
            int cont = 0;
            fis = ctx.openFileInput(Archivo);
            int i = 2;
            char caracter;
            while (i > 0) {
                i = fis.read();
                caracter = (char) i;
                lectura += caracter;
                if (i == '\n') {
                    if(cont == 0){
                        name = lectura.trim();
                    }else if(cont == 1){
                        lastname = lectura.trim();
                    }
                    else if (cont == 2)
                    {
                        id = lectura.trim();
                    } else if(cont == 3){
                        pass = lectura.trim();
                        lista.add(new Estudiante(name, lastname, id, pass));
                        cont =-1;
                    }
                    lectura = "";
                    cont++;
                }
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return lista;
    }
}
