package com.example.deffontaine.voyageapresvoyage.Activities.PhotosList;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NoteViewModel;
import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.Others.DateInputMask;
import com.example.deffontaine.voyageapresvoyage.R;

public class ModificationPhotoInformationsActivity extends AppCompatActivity {

    private Note photo;
    private EditText titreView, dateView, tagsView;
    private DateInputMask date_note_mask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_photo_informations);
        this.photo = (Note)getIntent().getSerializableExtra("photo");
        ImageView imgView = findViewById(R.id.DetailPhoto);
        setPic(imgView, this.photo.getNote_description());

        titreView = findViewById(R.id.TitrePhoto);
        titreView.setText(this.photo.getNote_name());

        dateView = findViewById(R.id.DatePhoto);
        dateView.setText(this.photo.getNote_date());
        date_note_mask = new DateInputMask(dateView, getString(R.string.formatDate));

        tagsView = findViewById(R.id.TagsPhoto);
        tagsView.setMovementMethod(new ScrollingMovementMethod());
        tagsView.setText(this.photo.getNote_tags());

        TextView textViewLieu = findViewById(R.id.LieuPhoto);
        if(this.photo.getNote_lieu_adresse()!=""){
            textViewLieu.setText(this.photo.getNote_lieu_adresse());
        }else{
            textViewLieu.setText(R.string.no_place);
        }
    }

    private void setPic(ImageView imgView, String imgPath) {

        int targetW = 1000;
        int targetH = 1000;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, bmOptions);
        imgView.setImageBitmap(bitmap);
    }

    public void modifierNote(View v){
        NoteViewModel mNoteViewModel;
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);


        if (TextUtils.isEmpty(titreView.getText().toString().trim()) || !(date_note_mask.isComplete())) {
            if (TextUtils.isEmpty(titreView.getText().toString().trim())){
                titreView.setError(getString(R.string.name_trip_required));
            }
            if (!(date_note_mask.isComplete())){
                dateView.setError(getString(R.string.date_note_required));
            }
        } else {
            photo.setNote_name(this.titreView.getText().toString());
            photo.setNote_date(this.dateView.getText().toString());
            photo.setNote_tags(this.tagsView.getText().toString());
            mNoteViewModel.updateNote(photo);
            Toast.makeText(
                    getApplicationContext(),
                    R.string.modif_photo_success,
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }
}

