package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("test", "test de log");

        Toast.makeText(this, "Test taost avec du foie gras. Miam", Toast.LENGTH_LONG).show();

        btnLife = findViewById(R.id.button2);

        btnLife.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Lifecycle.class);

            intent.putExtra("user", "admin");
            intent.putExtra("pwd", "123");

            Bundle bundle=new Bundle();
            bundle.putString("user", "admin");
            bundle.putString("pwd","1234");

            intent.putExtras(bundle);

            startActivity(intent);
        });
    }
}