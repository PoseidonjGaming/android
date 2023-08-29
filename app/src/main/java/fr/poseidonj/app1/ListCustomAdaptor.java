package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.poseidonj.app1.adapter.CustomAdapter;
import fr.poseidonj.app1.entity.Product;

public class ListCustomAdaptor extends AppCompatActivity {
    private ListView listView;
    private List<Product> products;
    private ArrayAdapter<Product> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_custom_adaptor);


        listView = findViewById(R.id.list);

        products = new ArrayList<>(Arrays.asList(new Product(1, "Desc", 120)));
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
//        listView.setAdapter(adapter);
        adapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, products);
        listView.setAdapter(adapter);
    }
}