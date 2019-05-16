package com.example.deffontaine.voyageapresvoyage.Activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deffontaine.voyageapresvoyage.Entities.Trip;
import com.example.deffontaine.voyageapresvoyage.R;

public class ModificationsTripAdapter extends RecyclerView.Adapter<ModificationsTripAdapter.ModificationsTripViewHolder>  {

    class ModificationsTripViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTripView;

        private ModificationsTripViewHolder(View itemView) {
            super(itemView);
            nameTripView = itemView.findViewById(R.id.nomVoyage);
//            nameTripView.setText(this.trip.getNom_trip());
        }

    }

    private final LayoutInflater mInflater;
    private Trip mTrip;

    ModificationsTripAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ModificationsTripAdapter.ModificationsTripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View noteView = mInflater.inflate(R.layout.recyclerview_trip, parent, false);
        return new ModificationsTripAdapter.ModificationsTripViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(ModificationsTripAdapter.ModificationsTripViewHolder holder, int position) {
        if (mTrip != null) {
            Trip current = mTrip;
            holder.nameTripView.setText(current.getNom_trip());
        } else {
            // Covers the case of data not being ready yet.
            //TODO
//            holder.nameTripView.setText(R.string.no_note);
        }
    }

    void setTrip(Trip trip){
        mTrip = trip;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTrip != null)
            return 1;
        else return 0;
    }

}
