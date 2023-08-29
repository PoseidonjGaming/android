package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> stringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list);
        fill();
    }

    private void fill() {
        List<String> lst = new ArrayList<>(Arrays.asList("Hello", "World!", "How", "Are", "You"));

        stringAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lst);
        listView.setAdapter(stringAdapter);


    }
}