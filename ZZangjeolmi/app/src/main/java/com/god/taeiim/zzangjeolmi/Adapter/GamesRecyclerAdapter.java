package com.god.taeiim.zzangjeolmi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.god.taeiim.zzangjeolmi.Model.GamesItem;
import com.god.taeiim.zzangjeolmi.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 9. 1..
 */

public class GamesRecyclerAdapter extends RecyclerView.Adapter<GamesRecyclerAdapter.ViewHolder>{
    ArrayList<GamesItem> gamesItem = new ArrayList<>();
    Context context;

    public GamesRecyclerAdapter(Context context, ArrayList<GamesItem> gamesItem){
        this.gamesItem = gamesItem;
        this.context = context;
    }

    @Override
    public GamesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gamelist,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GamesRecyclerAdapter.ViewHolder holder, int position) {
        holder.titleTv.setText(gamesItem.get(position).getGameName());
        holder.imgView.setImageResource(gamesItem.get(position).getImg());
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Animation anim = AnimationUtils.loadAnimation(context,R.anim.anim_scale);
                        holder.insideLayout.startAnimation(anim);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return gamesItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        ImageView imgView;
        LinearLayout insideLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.gamelist_titleTv);
            imgView = (ImageView) itemView.findViewById(R.id.gamelist_imgView);
            insideLayout = (LinearLayout) itemView.findViewById(R.id.gameCardLayout);
        }
    }
}
