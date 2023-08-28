package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CalcIMC extends AppCompatActivity {
    EditText inputTaille, intputPoids;
    TextView result;
    RadioButton rdCM;
    WebView wbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_imc);

        inputTaille=findViewById(R.id.inputTaille);
        intputPoids=findViewById(R.id.inputPoids);
        rdCM=findViewById(R.id.rbCM);
        wbView=findViewById(R.id.wvInterpretation);
        result=findViewById(R.id.tvResltatImc);
    }

    public void btnImcClick(View view) {
        try {
            double poids = Double.parseDouble(intputPoids.getText().toString());
            double taille = Double.parseDouble(inputTaille.getText().toString());

            double imc = poids / Math.pow((rdCM.isChecked())?taille/100: taille,2);
            result.setText(new DecimalFormat("#.##").format(imc));
            InputStream stream=getAssets().open("imc.html");
            Scanner scanner=new Scanner(stream, "utf-8").useDelimiter("\\A");
            wbView.loadData(scanner.hasNext()? scanner.next():"", "text/html","utf-8");
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}