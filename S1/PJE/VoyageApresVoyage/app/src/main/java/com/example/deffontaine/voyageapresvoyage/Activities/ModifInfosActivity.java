package com.example.deffontaine.voyageapresvoyage.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Others.DateInputMask;
import com.example.deffontaine.voyageapresvoyage.R;
import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

public class ModifInfosActivity extends AppCompatActivity {

    private Trip trip;
    private EditText editTextName, editTextDeb, editTextFin, editTextDesc, editTextLieu;
    private TripViewModel mTripViewModel;
    private DateInputMask date_deb_mask, date_fin_mask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_infos);

        this.trip = (Trip)getIntent().getSerializableExtra("trip");

        this.editTextName = findViewById(R.id.nom_voyage);
        this.editTextName.setText(this.trip.getNom_trip());

        this.editTextDeb = findViewById(R.id.deb_voyage);
        this.editTextDeb.setText(this.trip.getD_debut_trip());
        this.date_deb_mask = new DateInputMask(this.editTextDeb, getString(R.string.formatDate));

        this.editTextFin = findViewById(R.id.fin_voyage);
        this.editTextFin.setText(this.trip.getD_fin_trip());
        this.date_fin_mask = new DateInputMask(this.editTextFin, getString(R.string.formatDate));

        this.editTextLieu = findViewById(R.id.lieu_voyage);
        this.editTextLieu.setText(this.trip.getLieu_trip());

        this.editTextDesc = findViewById(R.id.description_voyage);
        this.editTextDesc.setText(this.trip.getDescription_trip());
    }

    public void modifVoyage(View v){
        if (TextUtils.isEmpty(editTextName.getText().toString().trim()) || !(date_deb_mask.isComplete()) || !(date_fin_mask.isComplete())) {
            if (TextUtils.isEmpty(editTextName.getText().toString().trim())){
                editTextName.setError(getString(R.string.name_trip_required));
            }
            if (!(date_deb_mask.isComplete()) || !(date_fin_mask.isComplete())){
                if (!(date_deb_mask.isComplete())){
                    editTextDeb.setError(getString(R.string.date_not_complete));
                }
                if (!(date_fin_mask.isComplete())){
                    editTextFin.setError(getString(R.string.date_not_complete));
                }
            }else{
                if(date_fin_mask.getDatecurrent_date().compareTo(date_deb_mask.getDatecurrent_date())<0){
                    editTextFin.setError(getString(R.string.date_end_before_begin));
                }
            }
        } else {
            if (date_fin_mask.getDatecurrent_date().compareTo(date_deb_mask.getDatecurrent_date()) < 0) {
                editTextFin.setError(getString(R.string.date_end_before_begin));
            } else {
                this.trip.setNom_trip(this.editTextName.getText().toString());
                this.trip.setD_debut_trip(this.editTextDeb.getText().toString());
                this.trip.setD_fin_trip(this.editTextFin.getText().toString());
                this.trip.setLieu_trip(this.editTextLieu.getText().toString());
                this.trip.setDescription_trip(this.editTextDesc.getText().toString());
                this.mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
                this.mTripViewModel.updateTrip(this.trip);
                Toast.makeText(
                        getApplicationContext(),
                        R.string.modif_trip_success,
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
