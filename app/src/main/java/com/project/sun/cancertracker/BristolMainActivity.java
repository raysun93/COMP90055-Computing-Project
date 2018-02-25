package com.project.sun.cancertracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.sun.cancertracker.Global.Common;
import com.project.sun.cancertracker.models.Bristol;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BristolMainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton[] btn = new ImageButton[7];
    private TextView quality;
    private ImageButton btn_unfocus;
    private int[] btn_id = {R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,R.id.btn_5,R.id.btn_6,R.id.btn_7};
    private int selectedButton;
    private int score;
    private String amount;
    private FirebaseDatabase database;
    private DatabaseReference bristol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bristol_main);
        database = FirebaseDatabase.getInstance();
        bristol = database.getReference("Bristol");
        ToggleButton bristolAmount = findViewById(R.id.amount_choice);
        btn_unfocus = findViewById(R.id.btn_4);
        quality = findViewById(R.id.bristolQuality);
        Button submit = findViewById(R.id.btn_bristolSubmit);
        Button cancel = findViewById(R.id.btn_bristolCancel);
        selectedButton = 4;
        score = 4;
        amount = "Huge";
        quality.setTextColor(Color.GREEN);
        quality.setText("Good");
        for(int i = 0; i < btn.length; i++){
            btn[i] =  findViewById(btn_id[i]);

            btn[i].setOnClickListener(this);
        }
        bristolAmount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!compoundButton.isChecked()){
                    amount = "Huge";
                }else{
                    amount = "Little";
                }
            }
        });

        final Bristol bristolS = new Bristol();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentDate = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.UK);
                String date =df.format(currentDate);
                bristolS.setAmount(amount);
                bristolS.setScore(score);
                bristolS.setDateTime(date);
                bristol.child(Common.currentUser.getUserName()).child(date).setValue(bristolS);
                Toast.makeText(BristolMainActivity.this,"finish this Poop log success",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_1:
                setFocus(btn_unfocus, btn[0],1);
                this.score = 1;
                quality.setTextColor(Color.RED);
                quality.setText("Bad");
                break;

            case R.id.btn_2:
                setFocus(btn_unfocus, btn[1],2);
                this.score = 2;
                quality.setTextColor(Color.RED);
                quality.setText("Bad");
                break;

            case R.id.btn_3:
                setFocus(btn_unfocus, btn[2],3);
                this.score = 3;
                quality.setTextColor(Color.GREEN);
                quality.setText("Good");
                break;

            case R.id.btn_4:
                setFocus(btn_unfocus, btn[3],4);
                this.score = 4;
                quality.setTextColor(Color.GREEN);
                quality.setText("Good");
                break;

            case R.id.btn_5:
                setFocus(btn_unfocus, btn[4],5);
                this.score = 5;
                quality.setTextColor(Color.GREEN);
                quality.setText("Good");
                break;

            case R.id.btn_6:
                setFocus(btn_unfocus, btn[5],6);
                this.score = 6;
                quality.setTextColor(Color.RED);
                quality.setText("Bad");
                break;

            case R.id.btn_7:
                this.score = 7;
                setFocus(btn_unfocus, btn[6],7);
                quality.setTextColor(Color.RED);
                quality.setText("Bad");
                break;
        }
    }
    private void setFocus(ImageButton btn_unfocus, ImageButton btn_focus,int a){
        if(selectedButton == 1){
            btn_unfocus.setBackground(getDrawable(R.drawable.type_1));
        }
        if(selectedButton == 2){
            btn_unfocus.setBackground(getDrawable(R.drawable.type_2));
        }
        if(selectedButton == 3){
            btn_unfocus.setBackground(getDrawable(R.drawable.type_3));
        }
        if(selectedButton == 4){
            btn_unfocus.setBackground(getDrawable(R.drawable.type_4));
        }
        if(selectedButton == 5){
            btn_unfocus.setBackground(getDrawable(R.drawable.type_5));
        }
        if(selectedButton == 6){
            btn_unfocus.setBackground(getDrawable(R.drawable.type_6));
        }
        if(selectedButton == 7){
            btn_unfocus.setBackground(getDrawable(R.drawable.type_7));
        }
        if(a == 1){
            btn_focus.setBackground(getDrawable(R.drawable.type_1_choosed));
        }
        if(a == 2){
            btn_focus.setBackground(getDrawable(R.drawable.type_2_choosed));
        }
        if(a == 3){
            btn_focus.setBackground(getDrawable(R.drawable.type_3_choosed));
        }
        if(a == 4){
            btn_focus.setBackground(getDrawable(R.drawable.type_4_choosed));
        }
        if(a == 5){
            btn_focus.setBackground(getDrawable(R.drawable.type_5_choosed));
        }
        if(a == 6){
            btn_focus.setBackground(getDrawable(R.drawable.type_6_choosed));
        }
        if(a == 7){
            btn_focus.setBackground(getDrawable(R.drawable.type_7_choosed));
        }
        this.btn_unfocus = btn_focus;
        selectedButton = a;
    }
}
