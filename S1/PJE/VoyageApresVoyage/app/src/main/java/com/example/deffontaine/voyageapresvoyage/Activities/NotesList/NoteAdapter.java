package com.example.deffontaine.voyageapresvoyage.Activities.NotesList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteNameView, noteLieuView;

        private NoteViewHolder(View noteView) {
            super(noteView);
            noteNameView = noteView.findViewById(R.id.note_name);
            noteLieuView = noteView.findViewById(R.id.note_lieu);
        }
    }

    private final LayoutInflater mInflater;
    private List<Note> mNotesEcrites;

    NoteAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View noteView = mInflater.inflate(R.layout.recyclerview_note, parent, false);
        return new NoteViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        if (mNotesEcrites != null) {
            Note current = mNotesEcrites.get(position);
            holder.noteNameView.setText(current.getNote_name());
            String adresseComplete = current.getNote_lieu_adresse();
            if (!adresseComplete.isEmpty()){
                String[] adresseCoupe = adresseComplete.split(",");
                String adresse = adresseCoupe[adresseCoupe.length-2] + "," +adresseCoupe[adresseCoupe.length-1];
                holder.noteLieuView.setText(adresse.trim());
            }
        } else {
            // Covers the case of data not being ready yet.
            holder.noteNameView.setText(R.string.no_note);
        }
    }

    void setNotes(List<Note> notes){
        mNotesEcrites = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotesEcrites != null)
            return mNotesEcrites.size();
        else return 0;
    }

    public Note getNote(int position){
        return mNotesEcrites.get(position);
    }

}
