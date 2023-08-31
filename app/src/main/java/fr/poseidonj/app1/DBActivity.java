package fr.poseidonj.app1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.poseidonj.app1.entity.Note;
import fr.poseidonj.app1.utils.SQLBuilder;

public class DBActivity extends AppCompatActivity {
    ListView listNotes;

    ActivityResultLauncher<Intent> resultLauncher;
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

        registerForContextMenu(listNotes);

        resultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
            if(result.getResultCode()==RESULT_OK){
                assert result.getData() != null;
                if(result.getData().getBooleanExtra("refresh", false)){
                    notes.clear();
                    notes.addAll(builder.getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.note_menu, menu);
        menu.setHeaderTitle("Actions:");
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_detail -> Toast.makeText(this,
                    ((Note) listNotes.getItemAtPosition(((AdapterView.AdapterContextMenuInfo)
                            Objects.requireNonNull(item.getMenuInfo())).position)).getContent(), Toast.LENGTH_SHORT).show();
            case R.id.action_add ->
                    resultLauncher.launch(new Intent(this, AddOrEditActivity.class));
            case R.id.action_remove ->
                    new AlertDialog.Builder(this).setTitle("Remove note").setMessage("Yes or No")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                builder.delete(((AdapterView.AdapterContextMenuInfo) Objects.requireNonNull(item.getMenuInfo())).position);
                                adapter.notifyDataSetChanged();
                            })
                            .setNegativeButton("No", null).show();
            case R.id.action_update -> {
                Intent input = new Intent(this, AddOrEditActivity.class);
                input.putExtra("note", (Note) listNotes.getItemAtPosition(((AdapterView.AdapterContextMenuInfo)
                        Objects.requireNonNull(item.getMenuInfo())).position));
                resultLauncher.launch(input);
            }
            default -> {
                Toast.makeText(this, "No action found", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}