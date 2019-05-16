package com.example.deffontaine.voyageapresvoyage.Databases;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

import java.util.List;

@Dao
public interface TripDao  {

    @Insert
    long insert(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAll();

    @Delete
    void deleteTrip(Trip trip);

    @Query("SELECT * from trip_table ORDER BY trip_debut ASC")
    LiveData<List<Trip>> getAllTrips();

    @Update
    void updateTrip(Trip trip);

    @Query("SELECT * from trip_table where id = :id_trip")
    LiveData<Trip> getTrip(Integer id_trip);
    
}