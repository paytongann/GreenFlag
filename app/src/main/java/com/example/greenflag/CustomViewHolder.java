package com.example.greenflag;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView tv_name, tv_username, tv_age, tv_birthdate, tv_gender, tv_country, tv_postalcode, tv_currentname;
    ImageView iv_profile_pic, iv_current_pic;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.il_name);
        tv_username = itemView.findViewById(R.id.il_username);
        tv_age = itemView.findViewById(R.id.il_age);
        tv_birthdate = itemView.findViewById(R.id.il_birthDate);
        tv_gender = itemView.findViewById(R.id.il_gender);
        tv_country = itemView.findViewById(R.id.il_country);
        tv_postalcode = itemView.findViewById(R.id.il_postal_code);
        iv_profile_pic = itemView.findViewById(R.id.il_profile_pic);
        //iv_current_pic = itemView.findViewById(R.id.current_profile_pic);
        //tv_currentname= itemView.findViewById(R.id.current_name);
    }
}
