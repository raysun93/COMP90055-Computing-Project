package com.project.sun.cancertracker;

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
import com.project.sun.cancertracker.models.qolScore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class QolMainFragment extends Fragment {
    private View myFragment;
    private RecyclerView listQuestion;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter<Qol,QuestionViewHolder> adapter;

    private FirebaseDatabase database;
    private DatabaseReference questions,qolScores;

    private ArrayList<Integer> QolScore;
    private User user;


    public static QolMainFragment newInstance(){
        QolMainFragment qolMainFragment = new QolMainFragment();
        return qolMainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Question");
        qolScores = database.getReference("Qol");
        user = Common.currentUser;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button btn_Submit;
        initialqolScore();
        myFragment = inflater.inflate(R.layout.fragment_qol_main, container, false);
        listQuestion = myFragment.findViewById(R.id.listQuestion);
        listQuestion.setHasFixedSize(true);
        listQuestion.setItemViewCacheSize(52);
        layoutManager = new LinearLayoutManager(container.getContext());
        listQuestion.setLayoutManager(layoutManager);
        loadQuestions();
        btn_Submit = getActivity().findViewById(R.id.btn_submit);
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(QolScore.contains(-1)) {
                    Toast.makeText(getActivity(), "The survey has not completed", Toast.LENGTH_SHORT).show();
                } else {
                    Date currentDate = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
                    final String date = df.format(currentDate);

                    for (int i = 0; i < QolScore.size(); i++) {
                        qolScores.child(user.getUserName()).child(date).child(Integer.toString(i + 1)).setValue(QolScore.get(i));
                    }
                    Toast.makeText(getActivity(), "survey finish success", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
        });
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
                viewHolder.rb_1.setChecked(getId()==1);

                viewHolder.question_choice.setTag(position);
                viewHolder.question_choice.setOnCheckedChangeListener(null);

                viewHolder.rb_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setqolScore(position,1);
                    }
                });
                viewHolder.rb_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setqolScore(position,2);
                    }
                });
                viewHolder.rb_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setqolScore(position,3);
                    }
                });
                viewHolder.rb_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setqolScore(position,4);
                    }
                });
                viewHolder.question_choice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {


                    }
                });

            }
        };
        adapter.notifyDataSetChanged();
        listQuestion.setAdapter(adapter);
    }

    public void initialqolScore(){
        this.QolScore=new ArrayList<Integer>();
        for(int i = 0; i<51;i++){
            this.QolScore.add(-1);
        }
    }

    public void setqolScore(int position, int score){
        this.QolScore.set(position,score);
    }

}
