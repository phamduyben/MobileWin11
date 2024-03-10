package com.example.bai3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private static final String TAG = "SongAdapter";
    private final List<SongModel> mSongs;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public SongAdapter(List<SongModel> mSongs, Context mContext) {
        this.mSongs = mSongs;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        View itemView = mLayoutInflater.inflate(R.layout.row_item_song, parent, false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(
            @NonNull SongAdapter.SongViewHolder holder, int position
    ) {
        SongModel songModel = mSongs.get(position);
        holder.tvCode.setText(songModel.getmCode());
        holder.tvArtist.setText(songModel.getmArtist());
        holder.tvLyric.setText(songModel.getmLyric());
        holder.tvTitle.setText(songModel.getmTitle());
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCode;
        private TextView tvTitle;
        private TextView tvLyric;
        private TextView tvArtist;

        public SongViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            tvCode = itemView.findViewById(R.id.tv_code);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLyric = itemView.findViewById(R.id.tv_lyric);
            tvArtist = itemView.findViewById(R.id.tv_artist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SongModel song = mSongs.get(getAdapterPosition());
                    Toast.makeText(mContext, song.getmTitle(), Toast.LENGTH_SHORT)
                         .show();
                }
            });
        }

        public TextView getTvCode() {
            return tvCode;
        }

        public void setTvCode(TextView tvCode) {
            this.tvCode = tvCode;
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public void setTvTitle(TextView tvTitle) {
            this.tvTitle = tvTitle;
        }

        public TextView getTvLyric() {
            return tvLyric;
        }

        public void setTvLyric(TextView tvLyric) {
            this.tvLyric = tvLyric;
        }

        public TextView getTvArtist() {
            return tvArtist;
        }

        public void setTvArtist(TextView tvArtist) {
            this.tvArtist = tvArtist;
        }

    }
}
