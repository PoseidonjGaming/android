package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferenceActivity extends AppCompatActivity {
    EditText inputMail, inputPwd;
    Button btnLogin;
    CheckBox remember;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        inputMail=findViewById(R.id.inputMail);
        inputPwd=findViewById(R.id.inputPassword);

        remember=findViewById(R.id.checkBox);
        sharedPreferences= getSharedPreferences("remember", MODE_PRIVATE);

        restoreData();
    }



    public void btnLoginClick(View view) {
        if(remember.isChecked()){
            saveData();
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "No data Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveData() {


        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("mail", inputMail.getText().toString());
        edit.putString("pwd", inputPwd.getText().toString());
        edit.apply();

    }

    private void restoreData() {
        inputMail.setText(sharedPreferences.getString("mail", ""));
        inputPwd.setText(sharedPreferences.getString("pwd", ""));
    }
}