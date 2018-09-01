package com.god.taeiim.zzangjeolmi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.god.taeiim.zzangjeolmi.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 9. 1..
 */

public class GamesRecyclerAdapter extends RecyclerView.Adapter<GamesRecyclerAdapter.ViewHolder>{
    ArrayList<String> gamesItem = new ArrayList<>();

    public GamesRecyclerAdapter(ArrayList<String> gamesItem){
        this.gamesItem = gamesItem;
    }

    @Override
    public GamesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gamelist,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GamesRecyclerAdapter.ViewHolder holder, int position) {
        holder.titleTv.setText(gamesItem.get(position));
    }

    @Override
    public int getItemCount() {
        return gamesItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.gamelist_titleTv);
        }
    }
}
