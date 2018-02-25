package com.project.sun.cancertracker.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.sun.cancertracker.R;

/**
 * Created by sun on 2/21/2018.
 */

public class BristolHistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView bristolScore,bristolDate,bristolTime,bristolQuality;
    public ImageView bristolImage;

    public  BristolHistoryViewHolder(final View itemView){
        super(itemView);
        bristolScore = itemView.findViewById(R.id.bristol_score);
        bristolDate = itemView.findViewById(R.id.bristol_date);
        bristolTime = itemView.findViewById(R.id.bristol_time);
        bristolImage = itemView.findViewById(R.id.bristolType_image);
        bristolQuality = itemView.findViewById(R.id.bristol_quality);
    }
}
