package com.example.deffontaine.voyageapresvoyage.Activities.TripListAndDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deffontaine.voyageapresvoyage.R;
import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

import java.util.List;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder> {

    class TripViewHolder extends RecyclerView.ViewHolder {

        private final TextView tripItemView_name;
        private final TextView tripItemView_debut;
        private final TextView tripItemView_fin;

        private TripViewHolder(View itemView) {
            super(itemView);
            tripItemView_name = itemView.findViewById(R.id.trip_name);
            tripItemView_debut = itemView.findViewById(R.id.trip_debut);
            tripItemView_fin = itemView.findViewById(R.id.trip_fin);
        }

    }

    private final LayoutInflater mInflater;
    private List<Trip> mTrips;

    public TripListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TripViewHolder holder, int position) {
        if (mTrips != null) {
            Trip current = mTrips.get(position);
            holder.tripItemView_name.setText(current.getNom_trip());
            holder.tripItemView_debut.setText(current.getD_debut_trip());
            holder.tripItemView_fin.setText(current.getD_fin_trip()); // Pour la liste des noms de voyage
        }
        else {
            holder.tripItemView_name.setText(R.string.no_trip);
        }
    }

    public void setTrips(List<Trip> trips){
        mTrips = trips;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTrips != null)
            return mTrips.size();
        else return 0;
    }

    public Trip getTrip(int position){
        return mTrips.get(position);
    }
}