package com.example.greenflag;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserProfiles extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    String name, username, age, birthDate, country, gender, postalCode;
    byte [] photo;
    ProfileType profileType = new ProfileType(name, username, photo, age, birthDate, country, gender, postalCode);
    ImageView currentProfile;
    TextView currentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profiles);
        recyclerView = findViewById(R.id.rv_list_task);
        currentProfile = findViewById(R.id.current_profile_pic);
        currentName = findViewById(R.id.current_name);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        readTask();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Android Assignment1");
    }

    public void readTask(){
        ToDoDatabase toDoDatabase = new ToDoDatabase(this);
        SQLiteDatabase readableDB = toDoDatabase.getReadableDatabase();

        Cursor cursor = readableDB.query(
                DatabaseUtil.TaskTable.tableName,
                null,
                null,
                null,
                null,
                null,
                null);
        List<ProfileType> dataset = new ArrayList<>();
        ProfileType item;
        while(cursor.moveToNext()){
            String itemName =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseUtil.TaskTable.nameColumn
                            )
                    );
            String itemUsername =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseUtil.TaskTable.usernameColumn
                            )
                    );
            byte [] itemPhoto =
                    cursor.getBlob(
                            cursor.getColumnIndexOrThrow(
                                    (DatabaseUtil.TaskTable.photoColumn)
                            )
                    );
            String itemAge =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseUtil.TaskTable.ageColumn
                            )
                    );
            String itemBirthDate =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseUtil.TaskTable.birthDateColumn
                            )
                    );
            String itemCountry =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseUtil.TaskTable.countryColumn
                            )
                    );
            String itemGender =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseUtil.TaskTable.genderColumn
                            )
                    );
            String itemPostalCode =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseUtil.TaskTable.postalCodeColumn
                            )
                    );
            item = new ProfileType(itemName, itemUsername, itemPhoto, itemAge, itemBirthDate, itemCountry, itemGender, itemPostalCode);
            dataset.add(item);
        }
        adapter = new CustomAdapter(dataset);
        recyclerView.setAdapter(adapter);
        //String currentNameString = getIntent().getStringExtra("Name");
        //currentName.setText(currentNameString);
    }
}
