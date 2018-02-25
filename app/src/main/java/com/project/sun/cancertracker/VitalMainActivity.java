package com.project.sun.cancertracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.sun.cancertracker.Global.Common;
import com.project.sun.cancertracker.models.LifeVital;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class VitalMainActivity extends AppCompatActivity {
    private MaterialEditText edtWeight,edtHeight,edtFat,edtBloodLow,edtBloodHigh;
    private Button btn_submit,btn_cancel;
    private FirebaseDatabase database;
    private DatabaseReference lifeVital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_main);
        database = FirebaseDatabase.getInstance();
        lifeVital = database.getReference("LifeVital").child(Common.currentUser.getUserName());
        edtWeight = findViewById(R.id.edtWeight);
        edtHeight = findViewById(R.id.edtHeight);
        edtFat = findViewById(R.id.edtFat);
        edtBloodHigh = findViewById(R.id.edtBloodHigh);
        edtBloodLow = findViewById(R.id.edtBloodLow);
        btn_submit = findViewById(R.id.btn_vital_submit);
        btn_cancel = findViewById(R.id.btn_vital_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LifeVital lifevital = new LifeVital();
                if (!edtWeight.getText().toString().isEmpty() &&
                        !edtHeight.getText().toString().isEmpty() &&
                        !edtBloodHigh.getText().toString().isEmpty() &&
                        !edtBloodLow.getText().toString().isEmpty() &&
                        !edtFat.getText().toString().isEmpty()) {
                    if (isInteger(edtWeight.getText().toString()) &&
                            isInteger(edtHeight.getText().toString()) &&
                            isInteger(edtFat.getText().toString()) &&
                            isInteger(edtBloodHigh.getText().toString()) &&
                            isInteger(edtBloodLow.getText().toString())) {
                        int Weight = Integer.parseInt(edtWeight.getText().toString());
                        int Height = Integer.parseInt(edtHeight.getText().toString());
                        int Fat = Integer.parseInt(edtFat.getText().toString());
                        int BloodHigh = Integer.parseInt(edtBloodHigh.getText().toString());
                        int BloodLow = Integer.parseInt(edtBloodLow.getText().toString());
                        if (Weight > 20 && Weight < 300 &&
                                Height > 30 && Height < 300 &&
                                Fat > 1 && Fat < 55 && BloodHigh > 60 &&
                                BloodHigh < 300 && BloodLow > 30 &&
                                BloodLow < 200 && BloodHigh > BloodLow) {
                            Date currentDate = Calendar.getInstance().getTime();
                            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
                            final String date = df.format(currentDate);
                            lifevital.setHeight(Height);
                            lifevital.setWeight(Weight);
                            lifevital.setFat(Fat);
                            lifevital.setBloodHigh(BloodHigh);
                            lifevital.setBloodLow(BloodLow);
                            lifevital.setDate(date);
                            lifeVital.child(date).setValue(lifevital);
                            Toast.makeText(VitalMainActivity.this,"finish today's life vital successful",Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(VitalMainActivity.this, "Please valid range number in all boxes", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(VitalMainActivity.this, "Please enter Integer in all boxes", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(VitalMainActivity.this, "Please finish the test", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
