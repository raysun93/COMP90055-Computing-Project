package com.project.sun.cancertracker.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.sun.cancertracker.Interface.ItemClickListener;
import com.project.sun.cancertracker.R;

/**
 * Created by sun on 2/5/2018.
 */

public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView history_name;
    public ImageView history_image;

    private ItemClickListener itemClickListener;

    public HistoryViewHolder(View itemView) {
        super(itemView);
        history_image = itemView.findViewById(R.id.history_image);
        history_name = itemView.findViewById(R.id.history_name);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
