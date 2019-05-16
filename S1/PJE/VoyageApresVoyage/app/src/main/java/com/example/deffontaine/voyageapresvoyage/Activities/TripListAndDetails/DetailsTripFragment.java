package com.example.deffontaine.voyageapresvoyage.Activities.TripListAndDetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Activities.ModificationsTripActivity;
import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NoteViewModel;
import com.example.deffontaine.voyageapresvoyage.Activities.TripViewModel;
import com.example.deffontaine.voyageapresvoyage.R;
import com.example.deffontaine.voyageapresvoyage.Entities.Trip;

import javax.annotation.Nullable;

public class DetailsTripFragment extends Fragment{

    private Trip trip;
    private TripViewModel mTripViewModel;
    private NoteViewModel mNoteViewModel;
    private TextView textViewName, textViewDeb, textViewFin, textViewDesc, textViewLieu;

    public DetailsTripFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_trip, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey("trip")){
                this.trip = (Trip) args.getSerializable("trip");
                updateTrip(this.trip);
            }
        }

        view.findViewById(R.id.supprimer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimerVoyage(v);
            }
        });

        view.findViewById(R.id.consulter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulterEtModifier(v);
            }
        });
    }


    public void consulterEtModifier(View v){
        Intent modifIntent = new Intent(v.getContext(), ModificationsTripActivity.class);
        modifIntent.putExtra("trip", this.trip);
        startActivity(modifIntent);

        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        // Pour le mode portrait
        if (getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_portrait_details)!=null) {
            getActivity().finish();
        }
    }


    public void supprimerVoyage(final View v) {
        final Trip t = this.trip;
        final DetailsTripFragment d = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder
                .setMessage(R.string.supp_trip_question)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Suppression du voyage dans la base de données des voyages
                        mTripViewModel = ViewModelProviders.of(d).get(TripViewModel.class);
                        mTripViewModel.deleteTrip(t);
                        // Suppression des notes associées a ce voyage
                        mNoteViewModel = ViewModelProviders.of(d).get(NoteViewModel.class);
                        mNoteViewModel.deleteAllNotes(t.getId());
                        Toast.makeText(
                                v.getContext(),
                                R.string.supp_trip,
                                Toast.LENGTH_LONG).show();
                        Intent listIntent = new Intent(v.getContext(), ListTripsActivity.class);
                        startActivity(listIntent);
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

    public void updateTrip(Trip trip){
        this.trip = trip;
        this.textViewName = getView().findViewById(R.id.nom_voyage);
        textViewName.setText(trip.getNom_trip());

        this.textViewLieu = getView().findViewById(R.id.lieu);
        textViewLieu.setText(trip.getLieu_trip());

        this.textViewDeb = getView().findViewById(R.id.deb_voyage);
        textViewDeb.setText(trip.getD_debut_trip());

        this.textViewFin = getView().findViewById(R.id.fin_voyage);
        textViewFin.setText(trip.getD_fin_trip());

        this.textViewDesc = getView().findViewById(R.id.description_voyage);
        this.textViewDesc.setMovementMethod(new ScrollingMovementMethod());
        textViewDesc.setText(trip.getDescription_trip());
    }
}
