package com.example.deffontaine.voyageapresvoyage.Activities.VideosList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.deffontaine.voyageapresvoyage.Activities.NotesList.NoteViewModel;
import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.R;

public class DetailsVideosActivity extends AppCompatActivity {

    Note video;
    private NoteViewModel mVideoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_videos);
        this.video = (Note)getIntent().getSerializableExtra("video");

        TextView titreView = findViewById(R.id.TitreVideo);
        titreView.setText(this.video.getNote_name());

        TextView textViewLieu = findViewById(R.id.LieuVideo);

        if(this.video.getNote_lieu_adresse()!=""){
            textViewLieu.setText(this.video.getNote_lieu_adresse());
        }else{
            textViewLieu.setText(R.string.no_place);
        }

        TextView dateView = findViewById(R.id.DateVideo);
        dateView.setText(this.video.getNote_date());

        TextView tagsView = findViewById(R.id.TagsVideo);
        if (!this.video.getNote_tags().isEmpty()) {
            tagsView.setMovementMethod(new ScrollingMovementMethod());
            tagsView.setText(this.video.getNote_tags());
        }

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

    public void modifVideos(View v){
        Intent modifIntent = new Intent(v.getContext(), ModificationVideoInformationActivity.class);
        modifIntent.putExtra("video", video);
        startActivity(modifIntent);
        finish();
    }

    public void supprimerVideo(final View v){
        final Note n = this.video;
        final DetailsVideosActivity d = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder
                .setMessage(R.string.supp_video_question)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mVideoViewModel = ViewModelProviders.of(d).get(NoteViewModel.class);
                        mVideoViewModel.deleteNote(n);
                        Toast.makeText(
                                v.getContext(),
                                R.string.supp_video,
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
