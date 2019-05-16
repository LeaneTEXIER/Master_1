package com.example.deffontaine.voyageapresvoyage.Activities.NotesList;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Others.Lieu;
import com.example.deffontaine.voyageapresvoyage.Others.DateInputMask;
import com.example.deffontaine.voyageapresvoyage.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewNoteActivity extends AppCompatActivity {

    private EditText mEditNoteNameView, mEditNoteDescView, mEditNoteTags, mEditNoteDate;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private Intent replyIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        mEditNoteNameView = findViewById(R.id.name_note);
        mEditNoteDate = findViewById(R.id.date_note);
        final DateInputMask date_note_mask = new DateInputMask(mEditNoteDate, getString(R.string.formatDate));
        String note_date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        mEditNoteDate.setText(note_date);
        mEditNoteDescView = findViewById(R.id.desc_note);
        mEditNoteTags = findViewById(R.id.tags_note);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNoteNameView.getText().toString().trim()) || !(date_note_mask.isComplete())) {
                    if (TextUtils.isEmpty(mEditNoteNameView.getText().toString().trim())){
                        mEditNoteNameView.setError(getString(R.string.name_trip_required));
                    }
                    if (!(date_note_mask.isComplete())){
                        mEditNoteDate.setError(getString(R.string.date_note_required));
                    }
                } else {
                    getDataAndCreateNote();
                }
            }
        });

    }


    private void getDataAndCreateNote() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            createNote(location);
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
                            createNote(location);
                        }
                    });
        }
        else{
            createNote(null);
        }
    }

    private void createNote(Location location) {
        String note_name = mEditNoteNameView.getText().toString();
        String note_desc = mEditNoteDescView.getText().toString();
        String note_tags = mEditNoteTags.getText().toString();
        String note_date = mEditNoteDate.getText().toString();
        replyIntent.putExtra("note_name", note_name);
        replyIntent.putExtra("note_desc", note_desc);
        replyIntent.putExtra("note_date", note_date);
        replyIntent.putExtra("note_tags", note_tags);
        if (location != null) {
            replyIntent.putExtra("note_lieu_latitude", location.getLatitude());
            replyIntent.putExtra("note_lieu_longitude", location.getLongitude());
            // Récupère le lieu
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            Lieu lieu = new Lieu(geocoder, location.getLatitude(), location.getLongitude());
            String adresse = lieu.getAdresse();
            replyIntent.putExtra("note_lieu_adresse", adresse);
        }
        else {
                replyIntent.putExtra("note_lieu_latitude", 200);
                replyIntent.putExtra("note_lieu_longitude", 200);
                replyIntent.putExtra("note_lieu_adresse", "");
        }
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
