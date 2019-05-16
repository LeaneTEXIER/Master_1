package com.example.deffontaine.voyageapresvoyage.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.deffontaine.voyageapresvoyage.Entities.Trip;
import com.example.deffontaine.voyageapresvoyage.Databases.TripDao;
import com.example.deffontaine.voyageapresvoyage.Databases.TripRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TripRepository {

    private TripDao mTripDao;
    private LiveData<List<Trip>> mAllTrips;

    public TripRepository(Application application) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        mTripDao = db.tripDao();
        mAllTrips = mTripDao.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return mAllTrips;
    }

    public long insert (Trip trip) {
        AsyncTask<Trip, Void, Long> task = new insertAsyncTask(mTripDao).execute(trip);
        try {
            return task.get();
        } catch (ExecutionException e) {
            return -1;
        } catch (InterruptedException e) {
            return -1;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Trip, Void, Long> {

        private TripDao mAsyncTaskDao;

        insertAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Trip... params) {
            return mAsyncTaskDao.insert(params[0]);
        }


    }

    public void deleteAll()  {
        new deleteAllTripsAsyncTask(mTripDao).execute();
    }

    private static class deleteAllTripsAsyncTask extends AsyncTask<Void, Void, Void> {
        private TripDao mAsyncTaskDao;

        deleteAllTripsAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void deleteTrip(Trip trip)  {
        new deleteTripAsyncTask(mTripDao).execute(trip);
    }

    private static class deleteTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao mAsyncTaskDao;

        deleteTripAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params) {
            mAsyncTaskDao.deleteTrip(params[0]);
            return null;
        }
    }

    public void updateTrip(Trip trip)  {
        new updateTripAsyncTask(mTripDao).execute(trip);
    }

    private static class updateTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao mAsyncTaskDao;

        updateTripAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params) {
            mAsyncTaskDao.updateTrip(params[0]);
            return null;
        }
    }

    public LiveData<Trip> getTrip(Integer id_trip) {
        return mTripDao.getTrip(id_trip);
    }

}