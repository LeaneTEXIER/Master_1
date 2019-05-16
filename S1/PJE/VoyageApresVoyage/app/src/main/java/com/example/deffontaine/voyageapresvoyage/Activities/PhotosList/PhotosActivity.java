package com.example.deffontaine.voyageapresvoyage.Activities.PhotosList;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NoteViewModel;
import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.Others.Lieu;
import com.example.deffontaine.voyageapresvoyage.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PhotosActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int mId_trip;
    private String mCurrentPhotoPath;
    private NoteViewModel mNoteViewModel;
    private String date;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private List<Note> notesPhotos;
    private PhotoAdapter mPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        this.mId_trip = getIntent().getIntExtra("id_trip",-1);
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        final GridView gridview = (GridView) findViewById(R.id.gridViewPhotos);
        final PhotoAdapter adapter = new PhotoAdapter(this);
        mPhotoAdapter = adapter;
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                PhotoAdapter ad = (PhotoAdapter) gridview.getAdapter();
                Intent detailsPhotoIntent = new Intent(view.getContext(), DetailsPhotoActivity.class);
                detailsPhotoIntent.putExtra("photo", ad.getPhoto(position) );
                view.getContext().startActivity(detailsPhotoIntent);
            }
        });

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotesPhotos(mId_trip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                notesPhotos = notes;
                if(notesPhotos.size()==0){
                    Toast.makeText(PhotosActivity.this, R.string.no_photo, Toast.LENGTH_SHORT).show();
                }
                adapter.setPhotos(notes);
            }
        });

    }

    public void dispatchTakePictureIntentView(View v){
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        Date d = new Date();
        String nameDate = new SimpleDateFormat("ddMMyyyy_HHmmss").format(d);
        this.date = new SimpleDateFormat("dd/MM/yyyy").format(d);
        String imageFileName = "JPEG_" + nameDate + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            getDataAndCreatePhoto();
        }
        else{
            Toast.makeText(this, R.string.add_photo_not_taken, Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataAndCreatePhoto() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            createPhoto(location);
                        }
                    });
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            createPhoto(location);
                        }
                    });
        }
        else{
            createPhoto(null);
        }
    }


    private void createPhoto(Location location) {
        Note notePhoto;
        String tags = "";
        if (location != null) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            Lieu lieu = new Lieu(geocoder, location.getLatitude(), location.getLongitude());
            String adresse = lieu.getAdresse();
            notePhoto = new Note(this.mId_trip,
                    getResources().getString(R.string.name_photo_default_begin)+" "+this.date,
                    "Photo",
                    this.mCurrentPhotoPath,
                    adresse,
                    location.getLatitude(),
                    location.getLongitude(),
                    this.date,
                    tags);
        }
        else {
            notePhoto = new Note(this.mId_trip,
                    getResources().getString(R.string.name_photo_default_begin)+" "+this.date,
                    "Photo",
                    this.mCurrentPhotoPath,
                    "",
                    200.0,
                    200.0,
                    this.date,
                    tags);
        }
        mNoteViewModel.insert(notePhoto);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("currentPhotoPath", this.mCurrentPhotoPath);
        savedInstanceState.putString("date", this.date);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.mCurrentPhotoPath = savedInstanceState.getString("currentPhotoPath");
        this.date = savedInstanceState.getString("date");
    }


    public void showSelectedNotes (View v){
        mNoteViewModel.getAllNotesPhotos(mId_trip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                notesPhotos = notes;
            }
        });

        TextInputEditText textInput = findViewById(R.id.search);
        String searchTagsString = textInput.getText().toString().toLowerCase();
        if (!searchTagsString.isEmpty()){
            String[] searchTags = searchTagsString.split(" ");
            ArrayList<Note> listNotes =  (ArrayList<Note>) this.notesPhotos;
            ArrayList<Note> res = new ArrayList<Note>();
            // Pour tous les mots de la recherhe je regarde s'il est bien dans les tags des notes et je met à jour la liste en fonction
            for (String tag: searchTags) {
                res.clear();
                for (int i = 0 ; i<listNotes.size(); i++){
                    if (listNotes.get(i).getNote_tags().toLowerCase().contains(tag)) {
                        res.add(listNotes.get(i));
                    }
                }
                listNotes = (ArrayList<Note>) res.clone();
            }
            this.mPhotoAdapter.setPhotos(listNotes);

        }
        else{
            this.mPhotoAdapter.setPhotos(this.notesPhotos);
        }
    }
}
