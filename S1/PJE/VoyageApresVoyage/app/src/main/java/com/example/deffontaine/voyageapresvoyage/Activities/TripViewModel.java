package com.example.deffontaine.voyageapresvoyage.Activities;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.deffontaine.voyageapresvoyage.Entities.Trip;
import com.example.deffontaine.voyageapresvoyage.Repositories.TripRepository;

import java.util.List;

public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;

    private LiveData<List<Trip>> mAllTrips;

    public TripViewModel (Application application) {
        super(application);
        mRepository = new TripRepository(application);
        mAllTrips = mRepository.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() { return mAllTrips; }

    public long insert(Trip trip) { return mRepository.insert(trip); }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteTrip(Trip trip) {
        mRepository.deleteTrip(trip);
    }

    public void updateTrip(Trip trip) { mRepository.updateTrip(trip);}

    public LiveData<Trip> getTrip(Integer id_trip) {
        return mRepository.getTrip(id_trip);
    }
}
