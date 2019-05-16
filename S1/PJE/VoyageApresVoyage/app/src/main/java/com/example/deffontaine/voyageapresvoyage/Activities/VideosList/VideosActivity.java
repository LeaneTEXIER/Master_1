package com.example.deffontaine.voyageapresvoyage.Activities.VideosList;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NoteViewModel;
import com.example.deffontaine.voyageapresvoyage.Activities.TripListAndDetails.RecyclerItemClickListener;
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

public class VideosActivity extends AppCompatActivity {

    private int mId_trip;
    static final int REQUEST_TAKE_VIDEO = 1;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    private String mCurrentVideoPath;
    private NoteViewModel mNoteViewModel;
    private String date;
    private String time;
    private FusedLocationProviderClient mFusedLocationClient;
    private List<Note> videos;
    private VideoAdapter mVideoAdapter;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        this.mId_trip = getIntent().getIntExtra("id_trip",-1);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final VideoAdapter adapter = new VideoAdapter(this);
        this.mVideoAdapter = adapter;

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        VideoAdapter ad = (VideoAdapter) recyclerView.getAdapter();
                        Intent detailsVideosIntent = new Intent(view.getContext(), DetailsVideosActivity.class);
                        detailsVideosIntent.putExtra("video", ad.getVideo(position) );
                        view.getContext().startActivity(detailsVideosIntent);
                    }
                })
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotesVideos(mId_trip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                videos = notes;
                if(videos.size()==0){
                    Toast.makeText(VideosActivity.this, R.string.no_video, Toast.LENGTH_SHORT).show();
                }
                adapter.setVideos(notes);
            }
        });

    }

    public void dispatchTakeVideoIntentView(View v){
        dispatchTakeVideoIntent();
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File videoFile = null;
            try {
                videoFile = createVideoFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (videoFile != null) {
                Uri videoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        videoFile);

                takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI);
                startActivityForResult(takeVideoIntent, REQUEST_TAKE_VIDEO);
            }
        }
    }

    private File createVideoFile() throws IOException {
        // Create an image file name
        Date d = new Date();
        String nameDate = new SimpleDateFormat("ddMMyyyy_HHmmss").format(d);
        this.date = new SimpleDateFormat("dd/MM/yyyy").format(d);
        this.time = nameDate.substring(9,13);
        String videoFileName = "MP4_" + nameDate + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                videoFileName,  /* prefix */
                ".mp4",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentVideoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            getDataAndCreateVideo();
        }
        else{
            Toast.makeText(this, R.string.add_video_not_taken, Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataAndCreateVideo() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            createVideo(location);
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
                            createVideo(location);
                        }
                    });
        }
        else{
            createVideo(null);
        }
    }


    private void createVideo( Location location) {
        Note noteVideo;
        //TODO
        String tags = "";
        if (location != null) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            Lieu lieu = new Lieu(geocoder, location.getLatitude(), location.getLongitude());
            String adresse = lieu.getAdresse();
            noteVideo = new Note(this.mId_trip,
                    getResources().getString(R.string.name_video_default_begin)+" "+this.date+" "+getResources().getString(R.string.at)+" "+this.time.substring(0,2)+":"+this.time.substring(2),
                    "Video",
                    this.mCurrentVideoPath,
                    adresse,
                    location.getLatitude(),
                    location.getLongitude(),
                    this.date,
                    tags);
        }
        else {
            noteVideo = new Note(this.mId_trip,
                    getResources().getString(R.string.name_video_default_begin)+" "+this.date+" "+getResources().getString(R.string.at)+" "+this.time.substring(0,2)+":"+this.time.substring(2),
                    "Video",
                    this.mCurrentVideoPath,
                    "",
                    200.0,
                    200.0,
                    this.date,
                    tags);
        }
        mNoteViewModel.insert(noteVideo);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("currentVideoPath", this.mCurrentVideoPath);
        savedInstanceState.putString("date", this.date);
        savedInstanceState.putString("time", this.time);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.mCurrentVideoPath = savedInstanceState.getString("currentVideoPath");
        this.date = savedInstanceState.getString("date");
        this.time = savedInstanceState.getString("time");
    }


    public void showSelectedNotes (View v){
        mNoteViewModel.getAllNotesPhotos(mId_trip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                videos = notes;
            }
        });

        TextInputEditText textInput = findViewById(R.id.search);
        String searchTagsString = textInput.getText().toString().toLowerCase();
        if (!searchTagsString.isEmpty()){
            String[] searchTags = searchTagsString.split(" ");
            ArrayList<Note> listNotes =  (ArrayList<Note>) this.videos;
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
            this.mVideoAdapter.setVideos(listNotes);

        }
        else{
            this.mVideoAdapter.setVideos(this.videos);
        }
    }
}
