package com.example.deffontaine.voyageapresvoyage.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.deffontaine.voyageapresvoyage.Databases.NoteDao;
import com.example.deffontaine.voyageapresvoyage.Databases.NoteRoomDatabase;
import com.example.deffontaine.voyageapresvoyage.Entities.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;

    public NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
    }

    public LiveData<List<Note>> getAllNotesEcrites(Integer id_trip) {
        return mNoteDao.getAllNotesEcrites(id_trip);
    }

    public LiveData<List<Note>> getAllNotesPhotos(Integer id_trip) {
        return mNoteDao.getAllNotesPhotos(id_trip);
    }

    public LiveData<List<Note>> getAllNotesVideos(Integer id_trip) {
        return mNoteDao.getAllNotesVideos(id_trip);
    }

    public void insert (Note note) {
        new NoteRepository.insertAsyncTask(mNoteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteAll()  {
        new NoteRepository.deleteAllAsyncTask(mNoteDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao mAsyncTaskDao;

        deleteAllAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void deleteNote(Note note)  {
        new NoteRepository.deleteNoteAsyncTask(mNoteDao).execute(note);
    }

    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;

        deleteNoteAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.deleteNote(params[0]);
            return null;
        }
    }

    public void deleteAllNotes(Integer id_trip)  {
        new NoteRepository.deleteAllNotesAsyncTask(mNoteDao).execute(id_trip);
    }

    private static class deleteAllNotesAsyncTask extends AsyncTask<Integer, Void, Void> {
        private NoteDao mAsyncTaskDao;

        deleteAllNotesAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            mAsyncTaskDao.deleteAllNotes(params[0]);
            return null;
        }
    }

    public void updateNote(Note note)  {
        new NoteRepository.updateNoteAsyncTask(mNoteDao).execute(note);
    }

    private static class updateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;

        updateNoteAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.updateNote(params[0]);
            return null;
        }
    }
}
