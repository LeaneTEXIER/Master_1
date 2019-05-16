package com.example.deffontaine.voyageapresvoyage.Activities.TripListAndDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.deffontaine.voyageapresvoyage.R;
import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

public class DetailsTripActivity extends AppCompatActivity {

    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_trip);
        this.trip = (Trip)getIntent().getSerializableExtra("trip");
        configureAndShowDetailFragment(savedInstanceState);
    }

    private void configureAndShowDetailFragment(Bundle savedInstanceState){
        if (findViewById(R.id.fragment_portrait_details) != null) {
            if (savedInstanceState != null) {
                return; }
            DetailsTripFragment detailsTripFragment = new DetailsTripFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("trip", trip);
            detailsTripFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_portrait_details, detailsTripFragment)
                    .commit();
        }
    }


}
