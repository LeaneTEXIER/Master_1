package com.example.deffontaine.voyageapresvoyage.Activities.VideosList;

import android.arch.lifecycle.ViewModelProviders;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NoteViewModel;
import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.Others.DateInputMask;
import com.example.deffontaine.voyageapresvoyage.R;

public class ModificationVideoInformationActivity extends AppCompatActivity {

    Note video;
    EditText titreView, dateView, tagsView;
    private DateInputMask date_note_mask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_video_information);
        this.video = (Note)getIntent().getSerializableExtra("video");

        titreView = findViewById(R.id.TitreVideo);
        titreView.setText(this.video.getNote_name());

        TextView textViewLieu = findViewById(R.id.LieuVideo);
        if(this.video.getNote_lieu_adresse()!=""){
            textViewLieu.setText(this.video.getNote_lieu_adresse());
        }else{
            textViewLieu.setText(R.string.no_place);
        }

        dateView = findViewById(R.id.DateVideo);
        dateView.setText(this.video.getNote_date());
        date_note_mask = new DateInputMask(dateView, getString(R.string.formatDate));

        tagsView = findViewById(R.id.TagsVideo);
        tagsView.setMovementMethod(new ScrollingMovementMethod());
        tagsView.setText(this.video.getNote_tags());

        final VideoView videoView = findViewById(R.id.DetailVideo);
        videoView.setVideoPath(video.getNote_description());
        videoView.seekTo(1);

        final FloatingActionButton playPause = findViewById(R.id.PlayPauseVideoButton);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying()){
                    videoView.pause();
                    playPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
                }else{
                    videoView.start();
                    playPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                }
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
            }
        });
    }

    public void modifierNote(View v){
        NoteViewModel mNoteViewModel;
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        if (TextUtils.isEmpty(titreView.getText().toString().trim()) || !(date_note_mask.isComplete())) {
            if (TextUtils.isEmpty(titreView.getText().toString().trim())){
                titreView.setError(getString(R.string.name_trip_required));
            }
            if (!(date_note_mask.isComplete())){
                dateView.setError(getString(R.string.date_note_required));
            }
        } else {
            video.setNote_name(this.titreView.getText().toString());
            video.setNote_date(this.dateView.getText().toString());
            video.setNote_tags(this.tagsView.getText().toString());
            mNoteViewModel.updateNote(video);
            Toast.makeText(
                    getApplicationContext(),
                    R.string.modif_video_success,
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
