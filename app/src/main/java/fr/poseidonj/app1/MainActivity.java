package fr.poseidonj.app1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class MainActivity extends BaseActivity {

    Button btnLife;
    ActivityResultLauncher<Intent> imageResult;
    ImageView wv;

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

            Bundle bundle = new Bundle();
            bundle.putString("user", "admin");
            bundle.putString("pwd", "1234");

            intent.putExtras(bundle);

            startActivity(intent);
        });

        wv=findViewById(R.id.iv);

        imageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        wv.setImageBitmap((Bitmap) Objects.requireNonNull(result.getData().getExtras()).get("data"));
                        try {
                            File file=new File(getFilesDir()+"/test.png");
                            OutputStream stream=new BufferedOutputStream(new FileOutputStream(file));
                            ((Bitmap) Objects.requireNonNull(result.getData().getExtras().get("data"))).compress(Bitmap.CompressFormat.PNG, 90,stream);
                            stream.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        btnExit();
    }

    public void btnImcClick(View view) {
        Intent intent = new Intent(MainActivity.this, CalcIMC.class);
        startActivity(intent);
    }

    public void openUrlClick(View view) {
        String url = "https://www.google.fr";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);

    }

    public void callClick(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0000000000"));
        startActivity(intent);
    }

    public void smsClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0000000000"));
        startActivity(intent);
    }

    public void btnIntentForResultClick(View view) {
        Intent intent = new Intent(this, IntentForResult.class);
        startActivity(intent);
    }

    public void btnEmailClick(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:lmagnier@dawan.fr"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "sujet du mail...");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "body du mail....");

        //startActivity(emailIntent); //syntaxe outlook - option1

        //Syntaxe outlook - option2
        //startActivity(Intent.createChooser(emailIntent,"Envoi d'un mail outlook"));

        //Syntaxe si compte gmail configuré sur le tél:
        Intent gmailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:?subject=test email&to=lmagnier@dawan.fr&body=contenu du mail.."));
        startActivity(gmailIntent);

    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void clickCam(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageResult.launch(intent);
    }

    public void listClick(View view) {
        Intent intent=new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void listCustomAdapterClick(View view) {
        startActivity(new Intent(this, ListCustomAdaptor.class));
    }

    public void sharedPrefClick(View view) {
        startActivity(new Intent(this, SharedPreferenceActivity.class));
    }

    public void externalClick(View view) {

    }

    public void setAuthClick(View view) {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void bddClick(View view) {
        startActivity(new Intent(this, DBActivity.class));
    }

    public void apiClick(View view) {
        startActivity(new Intent(this, ApiActivity.class));
    }
}