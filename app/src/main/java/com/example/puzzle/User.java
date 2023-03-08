package com.example.puzzle;

public class User {
    String nombre;
    int puntos;

    public User(String nombre){
        nombre=nombre;
        puntos=0;
    }

    public String getUser(){
        return nombre;
    }

    public void SetName(String Nombre){
        nombre=Nombre;
    }

    public int getPuntos(){
        return puntos;
    }

    public void RegistroPuntos(int pts){
        puntos+=pts;
    }
}







