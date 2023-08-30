package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void btnExit(){
        findViewById(R.id.btnExit).setOnClickListener(v -> {
            BaseActivity.this.finishAffinity();
        });
    }
}