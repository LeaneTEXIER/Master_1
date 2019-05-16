package com.example.deffontaine.voyageapresvoyage.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Activities.TripListAndDetails.ListTripsActivity;
import com.example.deffontaine.voyageapresvoyage.R;
import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

public class HomeActivity extends AppCompatActivity {

    private TripViewModel mTripViewModel;
    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
    }

    public void createATrip(View view) {
        // Create an Intent to start the new activity
        Intent createIntent = new Intent(this, CreateTripActivity.class);
        // Start the new activity.
        startActivityForResult(createIntent, NEW_TRIP_ACTIVITY_REQUEST_CODE);
    }

    public void listTrips(View view) {
        // Create an Intent to start the new activity
        Intent createIntent = new Intent(this, ListTripsActivity.class);
        // Start the new activity.
        startActivity(createIntent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Trip trip = new Trip(data.getStringExtra("nom_trip"), data.getStringExtra("deb_trip"), data.getStringExtra("fin_trip"),
                    data.getStringExtra("lieu_trip"), data.getStringExtra("desc_trip"));
            int id = (int)mTripViewModel.insert(trip);
            trip.setId(id);
            Toast.makeText(
                    getApplicationContext(),
                    R.string.add_trip_success,
                    Toast.LENGTH_LONG).show();
            // Lance la modification du voyage créé
            Intent modifyIntent = new Intent(this, ModificationsTripActivity.class);
            modifyIntent.putExtra("trip", trip);
            startActivity(modifyIntent);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.add_trip_fail,
                    Toast.LENGTH_LONG).show();
        }
    }
}
