package com.example.deffontaine.voyageapresvoyage.Activities.NotesList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.R;

public class DetailsNoteActivity extends AppCompatActivity {

    private Note note;
    private NoteViewModel mNoteViewModel;
    private TextView textViewName, textViewDesc, textViewTags, textViewLieu, textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);
        this.note = (Note)getIntent().getSerializableExtra("note");

        this.textViewName = findViewById(R.id.nom_note);
        textViewName.setText(note.getNote_name());

        this.textViewDate = findViewById(R.id.date_note);
        textViewDate.setText(note.getNote_date());

        this.textViewDesc = findViewById(R.id.description_note);
        this.textViewDesc.setMovementMethod(new ScrollingMovementMethod());
        textViewDesc.setText(note.getNote_description());

        this.textViewTags = findViewById(R.id.tags_note);
        this.textViewTags.setMovementMethod(new ScrollingMovementMethod());
        textViewTags.setText(note.getNote_tags());

        this.textViewLieu = findViewById(R.id.lieu_note);
        if(this.note.getNote_lieu_adresse()!=""){
            textViewLieu.setText(this.note.getNote_lieu_adresse());
        }else{
            textViewLieu.setText(R.string.no_place);
        }
    }

    public void modifierNote(final View v) {
        Intent modifIntent = new Intent(v.getContext(), ModificationNoteActivity.class);
        modifIntent.putExtra("note", note);
        startActivity(modifIntent);
        finish();
    }

    public void supprimerNote(final View v) {
        final Note n = this.note;
        final DetailsNoteActivity d = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder
                .setMessage(R.string.supp_note_question)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mNoteViewModel = ViewModelProviders.of(d).get(NoteViewModel.class);
                        mNoteViewModel.deleteNote(n);
                        Toast.makeText(
                                v.getContext(),
                                R.string.supp_note,
                                Toast.LENGTH_LONG).show();
                        finish();
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
}
