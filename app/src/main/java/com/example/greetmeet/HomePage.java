package com.example.greetmeet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class HomePage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        String username=getIntent().getStringExtra("username");
        TextView uname=findViewById(R.id.name);
        uname.setText(username);
    }
}
