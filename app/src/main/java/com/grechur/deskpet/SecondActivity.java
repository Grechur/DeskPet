package com.grechur.deskpet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.grechur.deskpet.utils.LiveDataBus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    TextView second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        second = findViewById(R.id.second);
        second.setOnClickListener(this);

        LiveDataBus.get().with("bye",String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(SecondActivity.this,s,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.second:
                LiveDataBus.get().with("bye").setValue("second view");
                break;
        }
    }
}
