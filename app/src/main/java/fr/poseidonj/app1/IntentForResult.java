package fr.poseidonj.app1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntentForResult extends AppCompatActivity {

    EditText input1, input2;
    Button btnCalc;
    TextView result;
    ActivityResultLauncher<Intent> dResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_for_result);

        input1 = findViewById(R.id.edNombre1);
        input2 = findViewById(R.id.edNombre2);

        result = findViewById(R.id.tvResultActivity);

        btnCalc = findViewById(R.id.btnCalculer);


        dResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result1 -> {
                    if (result1.getResultCode() == RESULT_OK && result1.getData() != null)
                        result.setText(String.valueOf(result1.getData().getDoubleExtra("result",0)));
                });

        btnCalc.setOnClickListener(view -> {
            Intent intent = new Intent(this, IntentForCalculActivity.class);
            intent.putExtra("nb1", Double.parseDouble(input1.getText().toString()));
            intent.putExtra("nb2", Double.parseDouble(input2.getText().toString()));
            dResult.launch(intent);
        });
    }
}