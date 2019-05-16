package com.example.deffontaine.voyageapresvoyage.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.deffontaine.voyageapresvoyage.Others.DateInputMask;
import com.example.deffontaine.voyageapresvoyage.R;

public class CreateTripActivity extends AppCompatActivity {

    private EditText mEditTripViewNom, mEditTripViewDeb, mEditTripViewFin, mEditTripViewLieu, mEditTripViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        this.mEditTripViewNom = findViewById(R.id.nom_voyage);
        this.mEditTripViewDeb = findViewById(R.id.deb_voyage);
        final DateInputMask date_deb_mask = new DateInputMask(this.mEditTripViewDeb, getString(R.string.formatDate));
        this.mEditTripViewFin = findViewById(R.id.fin_voyage);
        final DateInputMask date_fin_mask = new DateInputMask(this.mEditTripViewFin, getString(R.string.formatDate));
        this.mEditTripViewLieu = findViewById(R.id.lieu_voyage);
        this.mEditTripViewDescription = findViewById(R.id.description_voyage);

        final Button button = findViewById(R.id.creer_voyage);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTripViewNom.getText().toString().trim()) || TextUtils.isEmpty(mEditTripViewDeb.getText()) || TextUtils.isEmpty(mEditTripViewFin.getText()) ||
                        !(date_deb_mask.isComplete()) || !(date_fin_mask.isComplete())) {
                    if (TextUtils.isEmpty(mEditTripViewNom.getText().toString().trim())){
                        mEditTripViewNom.setError(getString(R.string.name_trip_required));
                    }
                    if (TextUtils.isEmpty(mEditTripViewDeb.getText()) || TextUtils.isEmpty(mEditTripViewFin.getText()) ||
                            !(date_deb_mask.isComplete()) || !(date_fin_mask.isComplete())){
                        if (TextUtils.isEmpty(mEditTripViewDeb.getText())){
                            mEditTripViewDeb.setError(getString(R.string.date_begin_trip_required));
                        }
                        else if (!(date_deb_mask.isComplete())){
                            mEditTripViewDeb.setError(getString(R.string.date_not_complete));
                        }
                        if (TextUtils.isEmpty(mEditTripViewFin.getText())){
                            mEditTripViewFin.setError(getString(R.string.date_end_trip_required));
                        }
                        else if (!(date_fin_mask.isComplete())){
                            mEditTripViewFin.setError(getString(R.string.date_not_complete));
                        }
                    }else{
                        if(date_fin_mask.getDatecurrent_date().compareTo(date_deb_mask.getDatecurrent_date())<0){
                            mEditTripViewFin.setError(getString(R.string.date_end_before_begin));
                        }
                    }
                } else {
                    if(date_fin_mask.getDatecurrent_date().compareTo(date_deb_mask.getDatecurrent_date())<0){
                        mEditTripViewFin.setError(getString(R.string.date_end_before_begin));
                    }else{
                        String tripNom = mEditTripViewNom.getText().toString();
                        String tripDeb = mEditTripViewDeb.getText().toString();
                        String tripFin = mEditTripViewFin.getText().toString();
                        String tripLieu = mEditTripViewLieu.getText().toString();
                        String tripDesc = mEditTripViewDescription.getText().toString();
                        replyIntent.putExtra("nom_trip", tripNom);
                        replyIntent.putExtra("deb_trip", tripDeb);
                        replyIntent.putExtra("fin_trip", tripFin);
                        replyIntent.putExtra("lieu_trip", tripLieu);
                        replyIntent.putExtra("desc_trip", tripDesc);
                        setResult(RESULT_OK, replyIntent);
                        finish();
                    }
                }
            }
        });
    }

}

