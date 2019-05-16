package com.example.deffontaine.voyageapresvoyage.Activities.VideosList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deffontaine.voyageapresvoyage.Entities.Note;
import com.example.deffontaine.voyageapresvoyage.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    class VideoViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteNameView, noteLieuView;

        private VideoViewHolder(View videoView) {
            super(videoView);
            noteNameView = videoView.findViewById(R.id.video_name);
            noteLieuView = videoView.findViewById(R.id.video_lieu);
        }
    }

    private final LayoutInflater mInflater;
    private List<Note> mVideos;

    VideoAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View videoView = mInflater.inflate(R.layout.recyclerview_video, parent, false);
        return new VideoViewHolder(videoView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        if (mVideos != null) {
            Note current = mVideos.get(position);
            holder.noteNameView.setText(current.getNote_name());
            String adresseComplete = current.getNote_lieu_adresse();
            if (!adresseComplete.isEmpty()){
                String[] adresseCoupe = adresseComplete.split(",");
                String adresse = adresseCoupe[adresseCoupe.length - 2] + "," + adresseCoupe[adresseCoupe.length - 1];
                holder.noteLieuView.setText(adresse.trim());
            }
        } else {
            // Covers the case of data not being ready yet.
            holder.noteNameView.setText(R.string.no_video);
        }
    }

    void setVideos(List<Note> notes){
        mVideos = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mVideos != null)
            return mVideos.size();
        else return 0;
    }

    public Note getVideo(int position){
        return mVideos.get(position);
    }
}
