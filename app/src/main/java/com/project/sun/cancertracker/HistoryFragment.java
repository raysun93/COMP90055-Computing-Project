package com.project.sun.cancertracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.sun.cancertracker.Global.Common;
import com.project.sun.cancertracker.Interface.ItemClickListener;
import com.project.sun.cancertracker.ViewHolder.HistoryViewHolder;
import com.project.sun.cancertracker.models.Bristol;
import com.project.sun.cancertracker.models.History;
import com.project.sun.cancertracker.models.User;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HistoryFragment extends Fragment {
    private View myFragment;
    private RecyclerView listHistory;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<History,HistoryViewHolder> adapter;

    private FirebaseDatabase database;
    private DatabaseReference categories;

    public static HistoryFragment newInstance(){
        HistoryFragment historyFragment = new HistoryFragment();
        return historyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Category");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_history, container, false);

        listHistory = myFragment.findViewById(R.id.listHistory);
        listHistory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listHistory.setLayoutManager(layoutManager);

        loadCategories();
        return myFragment;
    }

    private void loadCategories() {
        adapter = new FirebaseRecyclerAdapter<History, HistoryViewHolder>(
                History.class,
                R.layout.history_layout,
                HistoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(HistoryViewHolder viewHolder, final History model, int position) {
                viewHolder.history_name.setText(model.getName()+" history");
                Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.history_image);
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onClick(View view, int position, boolean IsLongClick) {

                        if(adapter.getRef(position).getKey().equals("01")) {
                            Common.currentApp = "Poo";
                            Intent BristolHistoryActivity = new Intent(getActivity(),BristolHistoryActivity.class);
                            startActivity(BristolHistoryActivity);
                        }else if(adapter.getRef(position).getKey().equals("02")){
                            Common.currentApp = "Qol";
                            Intent QolCalender = new Intent(getActivity(),CalendarActivity.class);
                            startActivity(QolCalender);
                        }else if(adapter.getRef(position).getKey().equals("03")) {
                            Common.currentApp = "Vital_life";
                            Intent VitalCalender = new Intent(getActivity(),CalendarActivity.class);
                            startActivity(VitalCalender);
                        }
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        listHistory.setAdapter(adapter);
    }
}
