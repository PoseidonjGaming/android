package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

    public void btnImcClick(View view) {
        Intent intent = new Intent(MainActivity.this, CalcIMC.class);
        startActivity(intent);
    }

    public void openUrlClick(View view) {
        String url="https://www.google.fr";
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);

    }

    public void callClick(View view) {
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:0000000000"));
        startActivity(intent);
    }

    public void smsClick(View view) {
        Intent intent=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0000000000"));
        startActivity(intent);
    }

    public void btnIntentForResultClick(View view) {
        Intent intent = new Intent(this, IntentForResult.class);
        startActivity(intent);
    }

    public void btnEmailClick(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:lmagnier@dawan.fr"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"sujet du mail...");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"body du mail....");

        //startActivity(emailIntent); //syntaxe outlook - option1

        //Syntaxe outlook - option2
        //startActivity(Intent.createChooser(emailIntent,"Envoi d'un mail outlook"));

        //Syntaxe si compte gmail configuré sur le tél:
        Intent gmailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:?subject=test email&to=lmagnier@dawan.fr&body=contenu du mail.."));
        startActivity(gmailIntent);

    }
}