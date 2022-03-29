package com.example.covidsurveyapp;

import android.widget.EditText;

public class ReadWriteUserDetails {
    public String fullName, city, dob, vaccineType, gender, pcr, symptoms, sideEffect;

    public ReadWriteUserDetails(String textFullName, String textCity, String textDob, String textGender, String textVaccineType, String textPCR, String textSideEffect, String textSymptoms){
        this.fullName = textFullName;
        this.city = textCity;
        this.dob = textDob;
        this.vaccineType = textVaccineType;
        this.gender = textGender;
        this.pcr = textPCR;
        this.symptoms = textSymptoms;
        this.sideEffect = textSideEffect;
    }
}
