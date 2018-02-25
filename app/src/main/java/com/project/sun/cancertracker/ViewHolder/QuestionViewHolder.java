package com.project.sun.cancertracker.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.project.sun.cancertracker.R;

/**
 * Created by sun on 2/11/2018.
 */

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    public TextView question_title;
    public RadioGroup question_choice;
    public RadioButton rb_1;
    public RadioButton rb_2;
    public RadioButton rb_3;
    public RadioButton rb_4;

    public QuestionViewHolder(final View itemView) {
        super(itemView);
        question_title = itemView.findViewById(R.id.question_title);
        question_choice = itemView.findViewById(R.id.question_choice);
        rb_1 = itemView.findViewById(R.id.not_at_all);
        rb_2 = itemView.findViewById(R.id.a_little);
        rb_3 = itemView.findViewById(R.id.quite_a_bit);
        rb_4 = itemView.findViewById(R.id.very_much);
    }

}
