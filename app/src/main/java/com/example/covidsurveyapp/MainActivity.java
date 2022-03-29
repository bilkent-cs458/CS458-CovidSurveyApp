package com.example.covidsurveyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.concurrent.locks.ReadWriteLock;

public class MainActivity extends AppCompatActivity {

    private EditText editTextRegisterFullName, editTextCity, editTextDob, editTextVaccineType;
    private ProgressBar progressBar;
    private RadioGroup radioGroupGender, radioGroupSideEffect, radioGroupPCR, radioGroupSymptoms;
    private RadioButton radioButtonGenderSelected, radioButtonSideEffectSelected, radioButtonPCRSelected, radioButtonSymptomsSelected;

    private DatePickerDialog picker;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Covid-19 Vaccine Survey");
        Toast.makeText(MainActivity.this, "You can start the survey now", Toast.LENGTH_LONG).show();

        editTextRegisterFullName = findViewById(R.id.editText_register_full_name);
        editTextCity = findViewById(R.id.editText_city);
        editTextDob = findViewById(R.id.editText_dob);
        editTextVaccineType = findViewById(R.id.editText_vaccine_type);

        progressBar = findViewById(R.id.progress_bar);

        //Radio button for gender, side effect, pcr, symptoms
        radioGroupGender = findViewById(R.id.radio_group_gender);
        radioGroupGender.clearCheck();

        radioGroupPCR = findViewById(R.id.radio_group_pcr);
        radioGroupPCR.clearCheck();

        radioGroupSideEffect = findViewById(R.id.radio_group_side_effect);
        radioGroupSideEffect.clearCheck();

        radioGroupSymptoms = findViewById(R.id.radio_group_symptoms);
        radioGroupSymptoms.clearCheck();

        //Setting up DatePicker on EditText
        editTextDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editTextDob.setText(dayOfMonth + "/" + (month+1) + "/" + year );
                    }
                },year, month, day);
                picker.show();
            }
        });

        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                radioButtonGenderSelected = findViewById(selectedGenderId);

                int selectedSideEffectId = radioGroupSideEffect.getCheckedRadioButtonId();
                radioButtonSideEffectSelected = findViewById(selectedSideEffectId);

                int selectedPCRId = radioGroupPCR.getCheckedRadioButtonId();
                radioButtonPCRSelected = findViewById(selectedPCRId);

                int selectedSymptomsId = radioGroupSymptoms.getCheckedRadioButtonId();
                radioButtonSymptomsSelected = findViewById(selectedSymptomsId);

                //Obtain the entered data
                String textFullName = editTextRegisterFullName.getText().toString();
                String textCity = editTextCity.getText().toString();
                String textDob = editTextDob.getText().toString();
                String textVaccineType = editTextVaccineType.getText().toString();
                String textGender, textPCR, textSideEffect, textSymptoms;

                if(TextUtils.isEmpty(textFullName)){
                    Toast.makeText(MainActivity.this, "Please enter your full name", Toast.LENGTH_LONG).show();
                    editTextRegisterFullName.setError("Full name is required");
                    editTextRegisterFullName.requestFocus();
                }else if(TextUtils.isEmpty(textCity)){
                    Toast.makeText(MainActivity.this, "Please enter your city", Toast.LENGTH_LONG).show();
                    editTextCity.setError("City is required");
                    editTextCity.requestFocus();
                }else if(TextUtils.isEmpty(textDob)){
                    Toast.makeText(MainActivity.this, "Please enter your date of birth", Toast.LENGTH_LONG).show();
                    editTextDob.setError("Date of Birth is required");
                    editTextDob.requestFocus();
                }else if(radioGroupGender.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this, "Please select your gender", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(textVaccineType)){
                    Toast.makeText(MainActivity.this, "Please enter your vaccine type", Toast.LENGTH_LONG).show();
                    editTextVaccineType.setError("Vaccine Type is required");
                    editTextVaccineType.requestFocus();
                }else if(radioGroupSideEffect.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this, "Please select your answer for Q-1", Toast.LENGTH_LONG).show();
                }else if(radioGroupPCR.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this, "Please select your answer for Q-2", Toast.LENGTH_LONG).show();
                }else if(radioGroupSymptoms.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this, "Please select your answer for Q-3", Toast.LENGTH_LONG).show();
                }else{
                    textGender = radioButtonGenderSelected.getText().toString();
                    textPCR = radioButtonPCRSelected.getText().toString();
                    textSideEffect = radioButtonSideEffectSelected.getText().toString();
                    textSymptoms = radioButtonSymptomsSelected.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    
                    registerUser(textFullName, textCity, textDob, textGender, textVaccineType, textPCR, textSideEffect, textSymptoms);
                    
                }

            }
        });

    }


    private void registerUser(String textFullName, String textCity, String textDob, String textGender, String textVaccineType, String textPCR, String textSideEffect, String textSymptoms) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInAnonymously().addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Survey is completed, Thank You!", Toast.LENGTH_LONG).show();
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    //Enter user data into the Firebase realtime database
                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textFullName, textCity, textDob, textGender, textVaccineType, textPCR, textSideEffect, textSymptoms);

                    //Extract user reference from database
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
                    referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                //Open survey is over and user data is taken successfully
                                Intent intent = new Intent(MainActivity.this, SurveySucceedActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish(); // to close main activity
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Failed obtaining the user data", Toast.LENGTH_LONG).show();

                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }else{
                    try{
                        throw task.getException();
                    }catch (Exception e){
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}