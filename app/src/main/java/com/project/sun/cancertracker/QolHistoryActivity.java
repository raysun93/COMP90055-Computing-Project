package com.project.sun.cancertracker;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.sun.cancertracker.Global.Common;

public class QolHistoryActivity extends AppCompatActivity {
    private Button btn_Back;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qol_history);
        setDefaultFragment();
        title = findViewById(R.id.QolHistoryTitle);
        title.setText("Qol Survey "+"("+ Common.selectedDate+")");
        btn_Back = findViewById(R.id.btn_back);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.QolHistoryFrame_layout, QolHistoryFragment.newInstance());
        transaction.commit();
    }
}
