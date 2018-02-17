package com.lzx.nicemusic.module.artist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzx.musiclibrary.aidl.model.SongInfo;
import com.lzx.nicemusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xian on 2018/2/15.
 */

public class ArtistSongAdapter extends RecyclerView.Adapter<ArtistSongAdapter.ArtistHolder> {
    private Context mContext;
    private List<SongInfo> mSongInfoList;
    private OnItemClickListener mOnItemClickListener;

    public ArtistSongAdapter(Context context) {
        mContext = context;
        mSongInfoList = new ArrayList<>();
    }

    public void setSongInfoList(List<SongInfo> songInfoList) {
        mSongInfoList = songInfoList;
        notifyDataSetChanged();
    }

    public List<SongInfo> getSongInfoList() {
        return mSongInfoList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist_song, parent, false);
        return new ArtistHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistHolder holder, int position) {
        SongInfo info = mSongInfoList.get(position);
        holder.mMusicNum.setText(String.valueOf(position + 1));
        holder.mMusicName.setText(info.getSongName());
        holder.itemView.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(info,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSongInfoList.size();
    }

    class ArtistHolder extends RecyclerView.ViewHolder {

        TextView mMusicNum, mMusicName;

        ArtistHolder(View itemView) {
            super(itemView);
            mMusicNum = itemView.findViewById(R.id.song_num);
            mMusicName = itemView.findViewById(R.id.song_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SongInfo info, int position);
    }
}