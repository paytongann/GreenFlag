package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAnAccount extends AppCompatActivity {
    private static final String TAG = "CreateAnAccount";

    EditText email;
    EditText createPassword;
    EditText repeatPassword;
    ImageView nextPage;
    ImageView emailTick;
    ImageView passwordTick;
    ImageView repeatPasswordTick;
    ImageView emailCross;
    ImageView passwordCross;
    ImageView repeatPasswordCross;
    Boolean validEmail = false;
    Boolean validPassword = false;
    boolean validRepeatedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);

        email = findViewById(R.id.et_email);
        createPassword = findViewById(R.id.et_create_password);
        repeatPassword = findViewById(R.id.et_repeat_password);
        nextPage = findViewById(R.id.iv_next_page);
        emailTick = findViewById(R.id.iv_tick_email);
        passwordTick = findViewById(R.id.iv_tick_password);
        repeatPasswordTick = findViewById(R.id.iv_tick_repeat_password);
        emailCross = findViewById(R.id.iv_cross_email);
        passwordCross = findViewById(R.id.iv_cross_create_password);
        repeatPasswordCross = findViewById(R.id.iv_cross_repeat_password);
        nextPage.setColorFilter(Color.rgb(123, 123, 123), android.graphics.PorterDuff.Mode.MULTIPLY);
        //Checks to see if inputted email is valid
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String emailString = email.getText().toString();
                if (emailCheck(emailString) == true){
                    emailTick.setVisibility(View.VISIBLE);
                    emailCross.setVisibility(View.INVISIBLE);
                    validEmail = true;
            }else{
                    emailCross.setVisibility(View.VISIBLE);
                    emailTick.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Checks to see if inputted password is valid
        createPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String createPasswordString = createPassword.getText().toString();
                    if (passwordCheck(createPasswordString) == true) {
                        passwordTick.setVisibility(View.VISIBLE);
                        passwordCross.setVisibility(View.INVISIBLE);
                        validPassword = true;
                } else {
                        passwordCross.setVisibility(View.VISIBLE);
                        passwordTick.setVisibility(View.INVISIBLE);
                    }
            }
        });

        //Checks to see if the inputted password and repeated password are the same
        repeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String createPasswordString = createPassword.getText().toString();
                String repeatPasswordString = repeatPassword.getText().toString();
                if (createPasswordString.equals(repeatPasswordString)) {
                    repeatPasswordTick.setVisibility(View.VISIBLE);
                    repeatPasswordCross.setVisibility(View.INVISIBLE);
                    validRepeatedPassword = true;
                    nextPage.setColorFilter(null);
                } else {
                    repeatPasswordCross.setVisibility(View.VISIBLE);
                    repeatPasswordTick.setVisibility(View.INVISIBLE);
                }
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validEmail == true) {
                    if (validPassword == true) {
                        if(validRepeatedPassword == true){
                        Intent intent = new Intent(
                                getBaseContext(), UserInformation.class
                        );
                        startActivity(intent);
                        }
                    }
                }
            }
        });
    }
    public boolean emailCheck(String str) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public boolean passwordCheck(String  str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if ((numberFlag && capitalFlag && lowerCaseFlag) && str.length() >7)
                return true;
        }
        return false;
    }

    public boolean samePasswordCheck(String password, String repeatPassword) {
        if (password.equals(repeatPassword) == true)
            return false;
        return true;
    }
}
