package com.example.deffontaine.voyageapresvoyage.Activities.NotesList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.Repositories.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;

    public NoteViewModel (Application application) {
        super(application);
        mRepository = new NoteRepository(application);
    }

    public LiveData<List<Note>> getAllNotesEcrites(Integer id_trip) {
        return mRepository.getAllNotesEcrites(id_trip);
    }

    public LiveData<List<Note>> getAllNotesPhotos(Integer id_trip) {
        return mRepository.getAllNotesPhotos(id_trip);
    }

    public LiveData<List<Note>> getAllNotesVideos(Integer id_trip) {
        return mRepository.getAllNotesVideos(id_trip);
    }

    public void insert(Note note) { mRepository.insert(note); }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteAllNotes(Integer id_trip){
        mRepository.deleteAllNotes(id_trip);
    }

    public void deleteNote(Note note) {
        mRepository.deleteNote(note);
    }

    public void updateNote(Note note) { mRepository.updateNote(note);}

}
