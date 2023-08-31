package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import fr.poseidonj.app1.entity.Note;
import fr.poseidonj.app1.utils.SQLBuilder;

public class AddOrEditActivity extends AppCompatActivity {

    EditText inputNoteText, inputNoteContent;
    Button btnAdd, btnCancel;

    boolean refresh;

    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit);

        inputNoteText = findViewById(R.id.inputNoteTitle);
        inputNoteContent = findViewById(R.id.inputNoteContent);

        btnAdd = findViewById(R.id.btnNoteAdd);
        btnCancel = findViewById(R.id.btnNoteCancel);

        btnCancel.setOnClickListener(v -> {
            refresh = false;
            onBackPressed();
        });

        btnAdd.setOnClickListener(v -> {
            SQLBuilder db = new SQLBuilder(this);
            if (note != null) {
                note.setContent(inputNoteContent.getText().toString());
                note.setTitle(inputNoteText.getText().toString());
                db.update(note);
            } else {
                db.add(new Note(inputNoteText.getText().toString(), inputNoteContent.getText().toString()));
            }
            db.close();
            refresh = true;
            onBackPressed();
        });

        note = (Note) getIntent().getSerializableExtra("note");

        if (note != null) {
            inputNoteText.setText(note.getTitle());
            inputNoteContent.setText(note.getContent());
        }


    }

    public void finish() {
        Intent intent = new Intent(this, DBActivity.class);
        intent.putExtra("refresh", refresh);
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }
}