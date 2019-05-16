package com.example.deffontaine.voyageapresvoyage.Activities.PhotosList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NoteViewModel;
import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.R;

public class DetailsPhotoActivity extends AppCompatActivity {

    private Note photo;
    private NoteViewModel mPhotoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_photo);
        this.photo = (Note)getIntent().getSerializableExtra("photo");
        ImageView imgView = findViewById(R.id.DetailPhoto);
        setPic(imgView, this.photo.getNote_description());

        TextView titreView = findViewById(R.id.TitrePhoto);
        titreView.setText(this.photo.getNote_name());

        TextView dateView = findViewById(R.id.DatePhoto);
        dateView.setText(this.photo.getNote_date());

        TextView tagsView = findViewById(R.id.TagsPhoto);
        if (!this.photo.getNote_tags().isEmpty()) {
            tagsView.setMovementMethod(new ScrollingMovementMethod());
            tagsView.setText(this.photo.getNote_tags());
        }

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

    public void modifPhoto(View v){
        Intent modifIntent = new Intent(v.getContext(), ModificationPhotoInformationsActivity.class);
        modifIntent.putExtra("photo", photo);
        startActivity(modifIntent);
        finish();
    }

    public void supprimerPhoto(final View v){
        final Note n = this.photo;
        final DetailsPhotoActivity d = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder
                .setMessage(R.string.supp_photo_question)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mPhotoViewModel = ViewModelProviders.of(d).get(NoteViewModel.class);
                        mPhotoViewModel.deleteNote(n);
                        Toast.makeText(
                                v.getContext(),
                                R.string.supp_photo,
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
