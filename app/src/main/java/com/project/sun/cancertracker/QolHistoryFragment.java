package com.project.sun.cancertracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.sun.cancertracker.Global.Common;
import com.project.sun.cancertracker.ViewHolder.QuestionViewHolder;
import com.project.sun.cancertracker.models.Qol;
import com.project.sun.cancertracker.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class QolHistoryFragment extends Fragment {
    private View myFragment;
    private RecyclerView listQuestion;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Qol,QuestionViewHolder> adapter;

    private FirebaseDatabase database;
    private DatabaseReference questions,qolScores;


    private User user;
    private String selectedDate;
    private ArrayList<Integer> checkedRb1;
    private ArrayList<Integer> checkedRb2;
    private ArrayList<Integer> checkedRb3;
    private ArrayList<Integer> checkedRb4;

    public static QolHistoryFragment newInstance(){
        QolHistoryFragment qolHistoryFragment = new QolHistoryFragment();
        return qolHistoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Question");
        qolScores = database.getReference("Qol");
        user = Common.currentUser;
        selectedDate = Common.selectedDate;
        checkedRb1 = new ArrayList<Integer>();
        checkedRb2 = new ArrayList<Integer>();
        checkedRb3 = new ArrayList<Integer>();
        checkedRb4 = new ArrayList<Integer>();
        /*Button back = getActivity().findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = checkedRb1.size()+checkedRb2.size()+checkedRb3.size()+checkedRb4.size();
                Toast.makeText(getActivity(),Integer.toString(i),Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_qol_history, container, false);
        listQuestion = myFragment.findViewById(R.id.listHistoryQuestion);
        listQuestion.setHasFixedSize(true);
        listQuestion.setItemViewCacheSize(1000);
        layoutManager = new LinearLayoutManager(container.getContext());
        listQuestion.setLayoutManager(layoutManager);
        qolScores.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(long i = 0; i < dataSnapshot.child(Common.currentUser.getUserName()).child(Common.selectedDate).getChildrenCount();i++){
                    String Score = dataSnapshot.child(Common.currentUser.getUserName()).child(Common.selectedDate).child(Integer.toString((int)i+1)).getValue().toString();
                    if(Score.equals("1")){
                        checkedRb1.add((int)i);
                    }
                    if(Score.equals("2")){
                        checkedRb2.add((int)i);
                    }
                    if(Score.equals("3")){
                        checkedRb3.add((int)i);
                    }
                    if(Score.equals("4")){
                        checkedRb4.add((int)i);
                    }
                }
                
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        loadQuestions();
        return myFragment;
    }

    private void loadQuestions() {

        adapter = new FirebaseRecyclerAdapter<Qol, QuestionViewHolder>(
                Qol.class,
                R.layout.question_layout,
                QuestionViewHolder.class,
                questions
        ) {

            @Override
            protected void populateViewHolder(final QuestionViewHolder viewHolder, final Qol model, final int position) {
                String questionTitle = adapter.getRef(position).getKey()+" "+model.getTitle();
                viewHolder.question_title.setText(questionTitle);
                viewHolder.rb_1.setId(position*100+1);
                viewHolder.rb_2.setId(position*100+2);
                viewHolder.rb_3.setId(position*100+3);
                viewHolder.rb_4.setId(position*100+4);
                viewHolder.rb_1.setClickable(false);
                viewHolder.rb_2.setClickable(false);
                viewHolder.rb_3.setClickable(false);
                viewHolder.rb_4.setClickable(false);
                if (checkedRb1.contains(position)){
                    viewHolder.rb_1.setChecked(true);
                }else {
                    viewHolder.rb_1.setChecked(false);
                }
                if (checkedRb2.contains(position)){
                    viewHolder.rb_2.setChecked(true);
                }else {
                    viewHolder.rb_2.setChecked(false);
                }
                if (checkedRb3.contains(position)){
                    viewHolder.rb_3.setChecked(true);
                }else {
                    viewHolder.rb_3.setChecked(false);
                }
                if (checkedRb4.contains(position)){
                    viewHolder.rb_4.setChecked(true);
                }else {
                    viewHolder.rb_4.setChecked(false);
                }

            }

        };
        adapter.notifyDataSetChanged();
        listQuestion.setAdapter(adapter);
    }
}
