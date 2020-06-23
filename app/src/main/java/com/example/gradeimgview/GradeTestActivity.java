package com.example.gradeimgview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class GradeTestActivity extends AppCompatActivity {

    private GradeImgView mGradeImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGradeImgView = findViewById(R.id.grade);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGradeImgView.setGradeNum(3);
            }
        });
    }
}
