package com.project.sun.cancertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.sun.cancertracker.Global.Common;
import com.project.sun.cancertracker.models.User;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    private CalendarPickerView calendar;
    private FirebaseDatabase database;
    private DatabaseReference QolHistory;
    private DatabaseReference VitalHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        database = FirebaseDatabase.getInstance();
        QolHistory = database.getReference("Qol");
        VitalHistory = database.getReference("LifeVital").child(Common.currentUser.getUserName());
        final Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR,-1);
        calendar = findViewById(R.id.calendar_view);
        calendar.setOnInvalidDateSelectedListener(null);

        calendar.setDateSelectableFilter(new CalendarPickerView.DateSelectableFilter() {
            Calendar cal=Calendar.getInstance();
            @Override
            public boolean isDateSelectable(final Date date) {
                boolean isSelectable = true;
                cal.setTime(date);
                if(cal.after(today)){
                    isSelectable = false;
                }
                return isSelectable;
            }
        });
        calendar.init(lastYear.getTime(), today.getTime());



        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                final User user = Common.currentUser;
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
                final String dateChoice =df.format(date);
                if(Common.currentApp.equals("Qol")) {
                    QolHistory.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(user.getUserName()).child(dateChoice).exists()) {
                                Common.selectedDate = dateChoice;
                                Intent QolHistoryActivity = new Intent(CalendarActivity.this, QolHistoryActivity.class);
                                startActivity(QolHistoryActivity);
                                Toast.makeText(CalendarActivity.this, "show the survey history", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CalendarActivity.this, "no survey this day", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else if(Common.currentApp.equals("Vital_life")){
                    VitalHistory.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(dateChoice).exists()){
                                Common.selectedDate = dateChoice;
                                Intent VitalHistoryActivity = new Intent(CalendarActivity.this, VitalHistoryActivity.class);
                                startActivity(VitalHistoryActivity);
                                Toast.makeText(CalendarActivity.this, "show the survey history", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(CalendarActivity.this, "no survey this day", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

    }
}
