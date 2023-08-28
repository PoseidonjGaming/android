package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Lifecycle extends AppCompatActivity {
    TextView txtVUser;
    TextView txtVPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();

        String user = getIntent().getStringExtra("user");

        txtVUser=findViewById(R.id.txtVUser);
        txtVUser.setText(user);
//
        txtVPwd=findViewById(R.id.txtVPwd);
        txtVPwd.setText(getIntent().getStringExtra("pwd"));

        //Toast.makeText(this, Objects.requireNonNull(getIntent().getExtras()).getString("user"), Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show();
    }
}