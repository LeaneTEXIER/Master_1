package com.example.deffontaine.voyageapresvoyage.Activities.TripListAndDetails;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Activities.TripViewModel;
import com.example.deffontaine.voyageapresvoyage.R;
import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

import java.util.List;
import javax.annotation.Nullable;

public class ListTripsFragment extends Fragment {

   private TripViewModel mTripViewModel;

   private OnTripSelectedListener mListener = null;

    public interface OnTripSelectedListener {
       void onTripSelected(Trip trip);
   }

    public ListTripsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_trips, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final TripListAdapter adapter = new TripListAdapter(view.getContext());

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                                TripListAdapter ad = (TripListAdapter) recyclerView.getAdapter();
                                Trip trip = ad.getTrip(position);
                                mListener.onTripSelected(trip);
                    }
                })
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
        mTripViewModel.getAllTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Trip> trips) {
                // Update the cached copy of the words in the adapter.
                if(trips.size()==0){
                    Toast.makeText(getActivity(), R.string.no_trip, Toast.LENGTH_SHORT).show();
                }
                adapter.setTrips(trips);
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTripSelectedListener) activity;
        } catch (ClassCastException e) {
            // Unchecked exception.
            throw new ClassCastException(activity.toString()
                    + " must implement OnTripSelectedListener");
        }
    }

}
