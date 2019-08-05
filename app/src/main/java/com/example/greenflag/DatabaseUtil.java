package com.example.greenflag;

import android.provider.BaseColumns;

public class DatabaseUtil {
    public static final String databaseName = "tododb";
    public static final int dabtabaseVersion = 1;

    public class TaskTable implements BaseColumns {
        public static final String tableName = "task";
        public static final String nameColumn = "name";
        public static final String usernameColumn = "username";
        public static final String photoColumn = "photo";
        public static final String ageColumn = "age";
        public static final String birthDateColumn = "birthDate";
        public static final String countryColumn = "country";
        public static final String genderColumn = "gender";
        public static final String postalCodeColumn = "postalCode";

    }
}
