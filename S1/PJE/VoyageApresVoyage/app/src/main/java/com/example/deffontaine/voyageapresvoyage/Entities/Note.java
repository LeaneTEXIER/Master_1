package com.example.deffontaine.voyageapresvoyage.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "note_table")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "note_id_trip")
    private int note_id_trip;

    @ColumnInfo(name = "note_name")
    private String note_name;

    @ColumnInfo(name = "note_type")
    private String note_type;

    @ColumnInfo(name = "note_description")
    private String note_description;

    @ColumnInfo(name = "note_lieu_adresse")
    private String note_lieu_adresse;

    @ColumnInfo(name = "note_lieu_latitude")
    private Double note_lieu_latitude;

    @ColumnInfo(name = "note_lieu_longitude")
    private Double note_lieu_longitude;

    @ColumnInfo(name = "note_date")
    private String note_date;

    @ColumnInfo(name = "note_tags")
    private String note_tags;

    public Note(int note_id_trip, String note_name, String note_type, String note_description, String note_lieu_adresse, Double note_lieu_latitude, Double note_lieu_longitude, String note_date, String note_tags){
        this.note_id_trip = note_id_trip;
        this.note_name = note_name;
        this.note_type = note_type;
        this.note_description = note_description;
        this.note_lieu_adresse = note_lieu_adresse;
        this.note_lieu_latitude = note_lieu_latitude;
        this.note_lieu_longitude = note_lieu_longitude;
        this.note_date = note_date;
        this.note_tags = note_tags;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public int getNote_id_trip(){ return this.note_id_trip;}

    public void setNote_id_trip(@NonNull int id){ this.note_id_trip = id;}

    public String getNote_name(){ return  this.note_name;}

    public void setNote_name(@NonNull String name){ this.note_name = name;}

    public String getNote_type(){ return this.note_type;}

    public void setNote_type(@NonNull String type){ this.note_type = type;}

    public String getNote_description(){ return this.note_description;}

    public void setNote_description(@NonNull String desc){ this.note_description=desc;}

    public String getNote_lieu_adresse() {
        return note_lieu_adresse;
    }

    public void setNote_lieu_adresse(String note_lieu_adresse) {
        this.note_lieu_adresse = note_lieu_adresse;
    }

    public Double getNote_lieu_latitude(){
        return this.note_lieu_latitude;
    }

    public void setNote_lieu_latitude(Double lieu){
        this.note_lieu_latitude = lieu;
    }

    public Double getNote_lieu_longitude(){
        return this.note_lieu_longitude;
    }

    public void setNote_lieu_longitude(Double lieu){
        this.note_lieu_longitude = lieu;
    }

    public String getNote_date(){
        return this.note_date;
    }

    public void setNote_date(String date){
        this.note_date = date;
    }

    public String getNote_tags(){
        return this.note_tags;
    }

    public void setNote_tags(String tags) {
        this.note_tags = tags;
    }
    }

