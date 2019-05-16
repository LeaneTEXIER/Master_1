package com.example.deffontaine.voyageapresvoyage.Databases;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.deffontaine.voyageapresvoyage.Entities.Note;

import java.util.List;
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();

    @Delete
    void deleteNote(Note note);

    @Query("SELECT * from note_table where note_type = 'Ecrit' and note_id_trip = :id_trip")
    LiveData<List<Note>> getAllNotesEcrites(Integer id_trip);

    @Query("SELECT * from note_table where note_type = 'Photo' and note_id_trip = :id_trip")
    LiveData<List<Note>> getAllNotesPhotos(Integer id_trip);

    @Query("SELECT * from note_table where note_type = 'Video' and note_id_trip = :id_trip")
    LiveData<List<Note>> getAllNotesVideos(Integer id_trip);

    @Query("delete from note_table where note_id_trip = :id_trip")
    void deleteAllNotes(Integer id_trip);

    @Update
    void updateNote(Note note);

}
