package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.poseidonj.app1.entity.Note;
import fr.poseidonj.app1.utils.SQLBuilder;

public class DBActivity extends AppCompatActivity {
    ListView listNotes;

    ArrayAdapter<Note> adapter;
    SQLBuilder builder;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);

        listNotes = findViewById(R.id.notes);

        builder = new SQLBuilder(this);
        builder.createTestData();
        notes = builder.getAll();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listNotes.setAdapter(adapter);
    }
}