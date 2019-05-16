package com.example.deffontaine.voyageapresvoyage.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NotesActivity;
import com.example.deffontaine.voyageapresvoyage.Activities.PhotosList.PhotosActivity;
import com.example.deffontaine.voyageapresvoyage.Activities.VideosList.VideosActivity;
import com.example.deffontaine.voyageapresvoyage.R;
import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

public class ModificationsTripActivity extends AppCompatActivity {

    private Trip trip;
    ModificationsTripAdapter mModificationsTripAdapter;
    private TripViewModel mTripViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifications_trip);
        this.trip = (Trip)getIntent().getSerializableExtra("trip");

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ModificationsTripAdapter adapter = new ModificationsTripAdapter(this);
        this.mModificationsTripAdapter= adapter;

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
        mTripViewModel.getTrip(trip.getId()).observe(this, new Observer<Trip>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final Trip t) {
                trip = t;
                adapter.setTrip(trip);
            }
        });
    }

    public void showInfo(View v){
        Intent modifTripIntent = new Intent(v.getContext(), ModifInfosActivity.class);
        modifTripIntent.putExtra("trip", this.trip);
        startActivity(modifTripIntent);
    }

    public void showNotes(View v){
        Intent notesIntent = new Intent(v.getContext(), NotesActivity.class);
        notesIntent.putExtra("id_trip", this.trip.getId());
        v.getContext().startActivity(notesIntent);
    }

    public void showPhotos(View v){
        Intent photosIntent = new Intent(v.getContext(), PhotosActivity.class);
        photosIntent.putExtra("id_trip", this.trip.getId());
        v.getContext().startActivity(photosIntent);
    }

    public void showMap(View v){
        Intent mapsIntent = new Intent(v.getContext(), MapsActivity.class);
        mapsIntent.putExtra("id_trip", this.trip.getId());
        v.getContext().startActivity(mapsIntent);
    }

    public void showVideos(View v){
        Intent videosIntent = new Intent(v.getContext(), VideosActivity.class);
        videosIntent.putExtra("id_trip", this.trip.getId());
        v.getContext().startActivity(videosIntent);
    }
}
