package com.example.greenflag;

import android.graphics.drawable.Drawable;
import android.view.contentcapture.DataRemovalRequest;

public class ProfileType {
    String name, username, age, birthDate, country, gender, postalCode;

    byte[] photo;

    public ProfileType(String name, String username, byte[] photo, String age, String birthDate, String country, String gender, String postalCode) {
        this.name = name;
        this.username = username;
        this.photo = photo;
        this.age = age;
        this.birthDate = birthDate;
        this.country = country;
        this.gender = gender;
        this.postalCode = postalCode;
    }
}
