package com.example.greenflag;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserInformation extends AppCompatActivity {

    ImageView save;
    RadioButton male;
    RadioButton female;
    EditText etPostalAddress;
    Button etCountry;
    Button etBirthDate;
    EditText etAge;
    ImageView ivProfilePic;
    EditText etPassword;
    EditText etUsername;
    EditText etName;
    ImageView ivChangePhoto;
    DatePickerDialog.OnDateSetListener mDateSetListner;
    private final int PHOTO_REQUEST_CODE = 404;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Android Assignment1");

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
        ivChangePhoto = findViewById(R.id.iv_change_photo);

        //Takes photo and changes the profile picture
        ivChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        //Creates calendar that user can select their birth date.
        etBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(UserInformation.this, android.R.style.Theme, mDateSetListner, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = (month + 1) + "/" + day + "/" + year;
                etBirthDate.setText(date);
            }
        };

        //Creates popup menu that lets user choose country
        etCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(UserInformation.this, etCountry);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_country_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        etCountry.setText(menuItem.getTitle());
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(UserInformation.this, "Enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    String username = etUsername.getText().toString();
                    if (username.equals("")) {
                        Toast.makeText(UserInformation.this, "Enter a username", Toast.LENGTH_SHORT).show();
                    } else {
                        String password = etPassword.getText().toString();
                        if (password.equals("")) {
                            Toast.makeText(UserInformation.this, "Enter a password", Toast.LENGTH_SHORT).show();
                        } else {
                            String age = etAge.getText().toString();
                            if (age.equals("")) {
                                Toast.makeText(UserInformation.this, "Enter an age", Toast.LENGTH_SHORT).show();
                            } else {
                                String birthDate = etBirthDate.getText().toString();
                                if (birthDate.equalsIgnoreCase("choose birth date")) {
                                    Toast.makeText(UserInformation.this, "Enter a birth date", Toast.LENGTH_SHORT).show();
                                } else {
                                    String country = etCountry.getText().toString();
                                    if (country.equalsIgnoreCase("choose a country")) {
                                        Toast.makeText(UserInformation.this, "Enter a country", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String gender;
                                        if (male.isChecked() == true) {
                                            gender = "Male";
                                        } else if (female.isChecked() == true) {
                                            gender = "Female";
                                        } else {
                                            gender = "Not-Specified";
                                        }
                                        String postalAddress = etPostalAddress.getText().toString();

                                        saveEntry(name, username, imageViewToByte(ivProfilePic), age, birthDate, country, gender, postalAddress);
                                        Intent intent = new Intent(
                                                getBaseContext(), UserProfiles.class
                                        );
                                        startActivity(intent);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            ;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PHOTO_REQUEST_CODE) {
            if (data != null) {
                Bundle samplePhoto = data.getExtras();
                //we get the small version of the picture.
                Bitmap rawData = (Bitmap) samplePhoto.get("data");
                ivProfilePic.setImageBitmap(rawData);
            }
        }
    }

    public void takePhoto() {
        Intent takePhotoIntent = getCameraIntent();
        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePhotoIntent, PHOTO_REQUEST_CODE);
        }
    }

    public Intent getCameraIntent() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        return intent;
    }

    public void saveEntry(String name, String username, byte[] photo, String age,
                          String birthDate, String country, String gender, String postalCode) {
        ToDoDatabase database = new ToDoDatabase(this);
        SQLiteDatabase saveData = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseUtil.TaskTable.nameColumn, name);
        values.put(DatabaseUtil.TaskTable.usernameColumn, username);
        values.put(DatabaseUtil.TaskTable.photoColumn, photo);
        values.put(DatabaseUtil.TaskTable.ageColumn, age);
        values.put(DatabaseUtil.TaskTable.birthDateColumn, birthDate);
        values.put(DatabaseUtil.TaskTable.countryColumn, country);
        values.put(DatabaseUtil.TaskTable.genderColumn, gender);
        values.put(DatabaseUtil.TaskTable.postalCodeColumn, postalCode);

        if (saveData.insert(DatabaseUtil.TaskTable.tableName,
                null,
                values) > 0) {
            Toast.makeText(this, "Saved values",
                    Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Nothing saved",
                    Toast.LENGTH_SHORT).show();
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
