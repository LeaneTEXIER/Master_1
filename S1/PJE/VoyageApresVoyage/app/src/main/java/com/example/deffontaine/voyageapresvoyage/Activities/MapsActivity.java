package com.example.deffontaine.voyageapresvoyage.Activities;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;

import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.DetailsNoteActivity;
import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NoteViewModel;
import com.example.deffontaine.voyageapresvoyage.Activities.PhotosList.DetailsPhotoActivity;
import com.example.deffontaine.voyageapresvoyage.Activities.VideosList.DetailsVideosActivity;
import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.deffontaine.voyageapresvoyage.R;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private List<Note> notesEcrites;
    private List<Note> photos;
    private List<Note> videos;
    private NoteViewModel mNoteViewModel;
    private Integer  idTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.idTrip = getIntent().getIntExtra("id_trip", -1);
        getNotesEcrites();
        getPhotos();
        getVideos();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        enableMyLocation();
        displayDetailClickOnInfoTypes();
    }

    private void displayDetailClickOnInfoTypes(){
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                Note note = (Note) marker.getTag();
                if(note.getNote_type().equals("Photo")){
                    Intent detailsPhotoIntent = new Intent(MapsActivity.this, DetailsPhotoActivity.class);
                    detailsPhotoIntent.putExtra("photo", note);
                    startActivity(detailsPhotoIntent);
                }
                else if(note.getNote_type().equals("Ecrit")) {
                    Intent detailsNoteIntent = new Intent(MapsActivity.this, DetailsNoteActivity.class);
                    detailsNoteIntent.putExtra("note", (Note) marker.getTag());
                    startActivity(detailsNoteIntent);
                }
                else if(note.getNote_type().equals("Video")) {
                    Intent detailsVideoIntent = new Intent(MapsActivity.this, DetailsVideosActivity.class);
                    detailsVideoIntent.putExtra("video", note);
                    startActivity(detailsVideoIntent);
                }
            }
        });
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }

    private void getNotesEcrites(){
        this.mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        this.mNoteViewModel.getAllNotesEcrites(this.idTrip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                notesEcrites = notes;
                displayNotesEcrites();
            }
        });
    }

    private void displayNotesEcrites(){
        for (Note note: this.notesEcrites) {
            if(!note.getNote_lieu_adresse().isEmpty()){

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLng(note.getNote_lieu_latitude(), note.getNote_lieu_longitude()))
                        .title(note.getNote_name())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                Marker marker = mMap.addMarker(markerOptions);
                marker.setTag(note);
                marker.isInfoWindowShown();
            }
        }
    }

    private void getPhotos(){
        this.mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        this.mNoteViewModel.getAllNotesPhotos(this.idTrip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                photos = notes;
                displayPhotos();
            }
        });
    }

    private void displayPhotos(){
        for (Note note: this.photos) {
            if(!note.getNote_lieu_adresse().isEmpty()){

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLng(note.getNote_lieu_latitude(), note.getNote_lieu_longitude()))
                        .title(note.getNote_name())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                Marker marker = mMap.addMarker(markerOptions);
                marker.setTag(note);

            }
        }
    }

    private void getVideos(){
        this.mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        this.mNoteViewModel.getAllNotesVideos(this.idTrip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                videos = notes;
                displayVideos();
            }
        });
    }

    private void displayVideos(){
        for (Note note: this.videos) {
            if(!note.getNote_lieu_adresse().isEmpty()){

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLng(note.getNote_lieu_latitude(), note.getNote_lieu_longitude()))
                        .title(note.getNote_name())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                Marker marker = mMap.addMarker(markerOptions);
                marker.setTag(note);

            }
        }
    }

    public void showSelectedNotes(View v){
        this.mMap.clear();
        boolean checkBoxPhotos = false;
        boolean checkBoxNotes = false;
        boolean checkBoxVideos = false;

        CheckBox cp = findViewById(R.id.checkbox_photos);
        checkBoxPhotos = cp.isChecked();

        CheckBox cn = findViewById(R.id.checkbox_notes);
        checkBoxNotes = cn.isChecked();

        CheckBox cv = findViewById(R.id.checkbox_videos);
        checkBoxVideos = cv.isChecked();

        if(checkBoxPhotos){
            this.displayPhotos();
        }

        if(checkBoxNotes){
            this.displayNotesEcrites();
        }

        if(checkBoxVideos){
            this.displayVideos();
        }
    }
}
