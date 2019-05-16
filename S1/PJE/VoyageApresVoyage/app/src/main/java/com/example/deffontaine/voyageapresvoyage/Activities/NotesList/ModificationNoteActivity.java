package com.example.deffontaine.voyageapresvoyage.Activities.NotesList;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.Others.DateInputMask;
import com.example.deffontaine.voyageapresvoyage.R;

public class ModificationNoteActivity extends AppCompatActivity {

    private NoteViewModel mNoteViewModel;
    private Note note;
    EditText nameNote, descNote, tagsNote, dateNote;
    private DateInputMask date_note_mask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_note);
        this.note = (Note)getIntent().getSerializableExtra("note");

        this.setInfos();

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAndSaveNoteOrShowErrors(v);
            }
        });
    }

    private void setInfos(){
        this.nameNote = findViewById(R.id.name_note);
        nameNote.setText(note.getNote_name());

        this.dateNote = findViewById(R.id.date_note);
        dateNote.setText(note.getNote_date());
        date_note_mask = new DateInputMask(dateNote, getString(R.string.formatDate));

        this.descNote = findViewById(R.id.desc_note);
        descNote.setText(note.getNote_description());

        this.tagsNote = findViewById(R.id.tags_note);
        tagsNote.setText(note.getNote_tags());
    }

    public void getDataAndSaveNoteOrShowErrors(final View v){
        if (TextUtils.isEmpty(nameNote.getText().toString().trim()) || !(date_note_mask.isComplete())) {
            if (TextUtils.isEmpty(nameNote.getText().toString().trim())){
                nameNote.setError(getString(R.string.name_trip_required));
            }
            if (!(date_note_mask.isComplete())){
                dateNote.setError(getString(R.string.date_note_required));
            }
        } else {
            note.setNote_name(this.nameNote.getText().toString());
            note.setNote_date(this.dateNote.getText().toString());
            note.setNote_description(this.descNote.getText().toString());
            note.setNote_tags(this.tagsNote.getText().toString());
            mNoteViewModel.updateNote(note);
            Toast.makeText(
                    getApplicationContext(),
                    R.string.modif_note_success,
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
