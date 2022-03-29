package com.example.covidsurveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SurveySucceedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_succeed);
        getSupportActionBar().setTitle("Thank you for joining Covid-19 Vaccine Survey");

    }
}