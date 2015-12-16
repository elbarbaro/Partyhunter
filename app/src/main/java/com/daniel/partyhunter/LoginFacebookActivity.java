package com.daniel.partyhunter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LoginFacebookActivity extends AppCompatActivity {

   ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginfacebook);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        imageView = (ImageView)findViewById(R.id.imagen_Inicial);
        Glide.with(this).load("http://partyhunter.mx/fotos/logo.png").into(imageView);







    }







}
