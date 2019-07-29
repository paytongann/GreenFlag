package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserInformation extends AppCompatActivity {

    ImageView save;
    RadioButton male;
    RadioButton female;
    EditText etPostalAddress;
    EditText etCountry;
    EditText etBirthDate;
    EditText etAge;
    ImageView ivProfilePic;
    EditText etPassword;
    EditText etUsername;
    EditText etName;
    DatePickerDialog.OnDateSetListener mDateSetListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> userInfo = new ArrayList<String>();
        String name;
        String username;
        String password;
        String photo;
        String age;
        final String birthDate;
        String country;
        String gender;
        String postalAddress;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        etCountry = findViewById(R.id.et_country);
        save = findViewById(R.id.iv_save);
        female = findViewById(R.id.radio_female);
        male = findViewById(R.id.radio_male);
        etPostalAddress = findViewById(R.id.et_postal_address);
        etBirthDate = findViewById(R.id.et_birth_date);
        etAge = findViewById(R.id.et_age);
        ivProfilePic = findViewById(R.id.iv_android_logo);
        etPassword = findViewById(R.id.et_password);
        etUsername = findViewById(R.id.et_username);
        etName = findViewById(R.id.et_name);

        name = etName.getText().toString();
        userInfo.add(name);

        username = etUsername.getText().toString();
        userInfo.add(username);

        password = etPassword.getText().toString();
        userInfo.add(password);

        photo = ivProfilePic.getDrawable().toString();
        userInfo.add(photo);

        age = etAge.getText().toString();
        userInfo.add(age);

        //Creates calendar that user can select their birth date.
        etBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(UserInformation.this, android.R.style.Theme_Black, mDateSetListner, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = (month+1)+ "/" +day+ "/" + year;
                etBirthDate.setText(date);
            }
        };

        birthDate = etBirthDate.getText().toString();
        userInfo.add(birthDate);

        country = etCountry.getText().toString();
        userInfo.add(country);

        if (male.isChecked() == true){
            gender = "Male";
        } else if (female.isChecked() == true){
            gender = "Female";
        } else {
            gender = "Not-Specified";
        }
        userInfo.add(gender);

        postalAddress = etPostalAddress.getText().toString();
        userInfo.add(postalAddress);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getBaseContext(), UserProfiles.class
                );
                startActivity(intent);
                    };
           });
        }
    }
