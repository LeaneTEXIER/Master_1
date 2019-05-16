package com.example.deffontaine.voyageapresvoyage.Activities.NotesList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Activities.TripListAndDetails.RecyclerItemClickListener;
import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.R;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private NoteViewModel mNoteViewModel;

    public static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;

    private Integer mId_trip;
    private List<Note> notesEcrites;
    NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        this.mId_trip = getIntent().getIntExtra("id_trip", -1);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final NoteAdapter adapter = new NoteAdapter(this);
        this.mNoteAdapter = adapter;

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        NoteAdapter ad = (NoteAdapter) recyclerView.getAdapter();
                        Intent detailsTripIntent = new Intent(view.getContext(), DetailsNoteActivity.class);
                        detailsTripIntent.putExtra("note", ad.getNote(position) );
                        view.getContext().startActivity(detailsTripIntent);
                    }
                })
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotesEcrites(mId_trip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                notesEcrites = notes;
                if(notesEcrites.size()==0){
                    Toast.makeText(NotesActivity.this, R.string.no_note, Toast.LENGTH_SHORT).show();
                }
                adapter.setNotes(notes);
            }
        });

        FloatingActionButton ajout = findViewById(R.id.ajout);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Note note = new Note(this.mId_trip, data.getStringExtra("note_name"), "Ecrit",data.getStringExtra("note_desc"), data.getStringExtra("note_lieu_adresse"),
                    data.getDoubleExtra("note_lieu_latitude", 200), data.getDoubleExtra("note_lieu_longitude", 200), data.getStringExtra("note_date"), data.getStringExtra("note_tags"));
            mNoteViewModel.insert(note);
            Toast.makeText(
                    getApplicationContext(),
                    R.string.add_note_success,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.add_note_fail,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void showSelectedNotes (View v){
        mNoteViewModel.getAllNotesEcrites(mId_trip).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable final List<Note> notes) {
                notesEcrites = notes;
            }
        });

        TextInputEditText textInput = findViewById(R.id.search);
        String searchTagsString = textInput.getText().toString().toLowerCase();
        if (!searchTagsString.isEmpty()){
            String[] searchTags = searchTagsString.split(" ");
            ArrayList<Note> listNotes =  (ArrayList<Note>) this.notesEcrites;
            ArrayList<Note> res = new ArrayList<Note>();
            // Pour tous les mots de la recherhe je regarde s'il est bien dans les tags des notes et je met à jour la liste en fonction
            for (String tag: searchTags) {
                res.clear();
                for (int i = 0 ; i<listNotes.size(); i++){
                    if (listNotes.get(i).getNote_tags().toLowerCase().contains(tag)) {
                        res.add(listNotes.get(i));
                    }
                }
                listNotes = (ArrayList<Note>) res.clone();
            }
            this.mNoteAdapter.setNotes(listNotes);

        }
        else{
            this.mNoteAdapter.setNotes(this.notesEcrites);
        }
    }
}