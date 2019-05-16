package com.example.deffontaine.voyageapresvoyage.Activities.TripListAndDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.deffontaine.voyageapresvoyage.R;
import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

public class ListTripsActivity extends AppCompatActivity implements ListTripsFragment.OnTripSelectedListener {

    public DetailsTripFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips);

        if (findViewById(R.id.fragment_portrait_list) == null) {
            if (getSupportFragmentManager().findFragmentById(R.id.details) != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(getSupportFragmentManager().findFragmentById(R.id.details)).commit();
            }
        }
    }

    @Override
    public void onTripSelected(Trip trip) {
        //Mode paysage
        if (findViewById(R.id.fragment_portrait_list) == null) {
            if(getSupportFragmentManager().findFragmentById(R.id.details) == null){
                this.detailsFragment = new DetailsTripFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.details, this.detailsFragment)
                        .commit();
                if(getSupportFragmentManager().executePendingTransactions()){
                    this.detailsFragment.updateTrip(trip);
                }
            }
            else{
                this.detailsFragment.updateTrip(trip);
            }
        } else { // mode portrait
                Intent detailsTripIntent = new Intent(getApplicationContext(), DetailsTripActivity.class);
                detailsTripIntent.putExtra("trip", trip );
                getApplicationContext().startActivity(detailsTripIntent);
        }
    }

}






