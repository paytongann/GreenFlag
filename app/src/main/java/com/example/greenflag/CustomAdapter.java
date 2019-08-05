package com.example.greenflag;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    String name, username, age, birthDate, country, gender, postalCode;
    byte[] photo;

    ProfileType profileType = new ProfileType(name, username, photo, age, birthDate, country, gender, postalCode);
    private List<ProfileType> dataSet;

    public CustomAdapter(List<ProfileType> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        CustomViewHolder customerViewHolder = new CustomViewHolder(view);
        //currentName.findViewById(R.id.current_name);
        //currentName.setText("Welcome back "+currentNameString+"!");
        return customerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_name.setText(
                dataSet.get(position).name
        );
        holder.tv_username.setText(
                dataSet.get(position).username
        );
        byte [] pic = dataSet.get(position).photo;
        Bitmap bitmap = BitmapFactory.decodeByteArray(pic,0, pic.length);
        holder.iv_profile_pic.setImageBitmap(bitmap);
        holder.tv_age.setText(
                dataSet.get(position).age
        );
        holder.tv_birthdate.setText(
                dataSet.get(position).birthDate
        );
        holder.tv_country.setText(
                dataSet.get(position).country
        );
        holder.tv_gender.setText(
                dataSet.get(position).gender
        );
        holder.tv_postalcode.setText(
                dataSet.get(position).postalCode
        );
        //holder.iv_current_pic.setImageBitmap(bitmap);
        //currentNameString = dataSet.get(position).name;
    }

    @Override
    public int getItemCount() {
        if (dataSet == null){
            return 0;
        } else {
            return dataSet.size();
            //ternary statement: return dataSet == null ?  0 : dataSet.size();
            // Question ?  true : false;
        }
    }
}