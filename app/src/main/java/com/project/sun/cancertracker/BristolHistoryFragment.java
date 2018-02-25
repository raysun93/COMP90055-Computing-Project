package com.project.sun.cancertracker;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.sun.cancertracker.Global.Common;
import com.project.sun.cancertracker.ViewHolder.BristolHistoryViewHolder;
import com.project.sun.cancertracker.ViewHolder.QuestionViewHolder;
import com.project.sun.cancertracker.models.Bristol;
import com.project.sun.cancertracker.models.Qol;
import com.project.sun.cancertracker.models.User;
import com.squareup.picasso.Picasso;


public class BristolHistoryFragment extends Fragment {
    private View myFragment;
    private RecyclerView listBristol;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Bristol,BristolHistoryViewHolder> adapter;

    private FirebaseDatabase database;
    private DatabaseReference bristol;
    private DatabaseReference bristolHistory;


    private User user;
    private String selectedDateTime;

    public static BristolHistoryFragment newInstance(){
        BristolHistoryFragment bristolHistoryFragment = new BristolHistoryFragment();
        return bristolHistoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        bristol = database.getReference("Bristol");
        bristolHistory = bristol.child(Common.currentUser.getUserName());
        user = Common.currentUser;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_bristol_history, container, false);
        listBristol = myFragment.findViewById(R.id.listBristol);
        listBristol.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listBristol.setLayoutManager(layoutManager);
        loadBristol();
        return myFragment;
    }

    private void loadBristol(){
        adapter = new FirebaseRecyclerAdapter<Bristol, BristolHistoryViewHolder>(
                Bristol.class,
                R.layout.bristol_history_layout,
                BristolHistoryViewHolder.class,
                bristolHistory
        ) {
            @Override
            protected void populateViewHolder(BristolHistoryViewHolder viewHolder, Bristol model, int position) {
                BristolImage image = new BristolImage();
                Picasso.with(getActivity()).load(image.getImage(model.getScore())).into(viewHolder.bristolImage);

                String divide[] = model.getDateTime().split(" ");
                String date = divide[0];
                String time = divide[1];
                if(model.getScore()>2&&model.getScore()<6) {
                    viewHolder.bristolQuality.setText("Good");
                    viewHolder.bristolQuality.setTextColor(Color.GREEN);
                }else{
                    viewHolder.bristolQuality.setText("Bad");
                    viewHolder.bristolQuality.setTextColor(Color.RED);
                }
                viewHolder.bristolDate.setText(date);
                viewHolder.bristolTime.setText(time);
                String score = "Amount: " + model.getAmount() + "  Score: "+ model.getScore();
                viewHolder.bristolScore.setText(score);
            }
        };
        adapter.notifyDataSetChanged();
        listBristol.setAdapter(adapter);
    }
}
