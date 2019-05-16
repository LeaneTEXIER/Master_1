package com.example.deffontaine.voyageapresvoyage.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "trip_table")
public class Trip implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "trip_nom")
    private String nom_trip;

    @ColumnInfo(name = "trip_debut")
    private String d_debut_trip;

    @ColumnInfo(name = "trip_fin")
    private String d_fin_trip;

    @ColumnInfo(name = "trip_lieu")
    private String lieu_trip;

    @ColumnInfo(name = "trip_description")
    private String description_trip;

    public Trip(String nom_trip, String d_debut_trip, String d_fin_trip, String lieu_trip, String description_trip) {
        this.nom_trip = nom_trip;
        this.d_debut_trip = d_debut_trip;
        this.d_fin_trip = d_fin_trip;
        this.lieu_trip = lieu_trip;
        this.description_trip = description_trip;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getNom_trip(){
        return this.nom_trip;
    }

    public void setNom_trip(@NonNull String nom) {
        this.nom_trip = nom;
    }

    public String getLieu_trip(){
        return this.lieu_trip;
    }

    public void setLieu_trip(String lieu) {
        this.lieu_trip = lieu;
    }

    public String getD_debut_trip(){
        return this.d_debut_trip;
    }

    public void setD_debut_trip(@NonNull String date) {
        this.d_debut_trip = date;
    }

    public String getD_fin_trip(){
        return this.d_fin_trip;
    }

    public void setD_fin_trip(@NonNull String date) {
        this.d_fin_trip = date;
    }

    public String getDescription_trip(){
        return this.description_trip;
    }

    public void setDescription_trip(String desc) {
        this.description_trip = desc;
    }

}
