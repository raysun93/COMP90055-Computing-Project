package com.project.sun.cancertracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.sun.cancertracker.Global.Common;
import com.project.sun.cancertracker.models.LifeVital;

public class VitalHistoryActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference lifeVital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_history);
        database = FirebaseDatabase.getInstance();
        lifeVital = database.getReference("LifeVital").child(Common.currentUser.getUserName());
        final TextView Title = findViewById(R.id.vital_history_title);
        final TextView WeightHistory = findViewById(R.id.WeightHistory);
        final TextView HeightHistory = findViewById(R.id.HeightHistory);
        final TextView FatHistory = findViewById(R.id.FatHistory);
        final TextView BloodHighHistory = findViewById(R.id.BloodHighHistory);
        final TextView BloodLowHistory = findViewById(R.id.BloodLowHistory);
        Button cancel = findViewById(R.id.btn_vitalHistory_cancel);
        lifeVital.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LifeVital lifevital = dataSnapshot.child(Common.selectedDate).getValue(LifeVital.class);
                String weight = "Weight: " + lifevital.getWeight() + "kg";
                String height = "Height: " + lifevital.getHeight() + "cm";
                String fat = "Body fat percentage: " + lifevital.getFat() + "%";
                String bloodhigh = "Blood pressure (systolic): " + lifevital.getBloodHigh();
                String bloodlow = "Blood pressure (diastolic): " + lifevital.getBloodLow();
                String title = "Life Vital (" + lifevital.getDate() +")";
                Title.setText(title);
                WeightHistory.setText(weight);
                HeightHistory.setText(height);
                FatHistory.setText(fat);
                BloodHighHistory.setText(bloodhigh);
                BloodLowHistory.setText(bloodlow);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
