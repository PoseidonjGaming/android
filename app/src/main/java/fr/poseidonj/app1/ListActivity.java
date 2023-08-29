package fr.poseidonj.app1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.poseidonj.app1.dialog.ProductDialog;
import fr.poseidonj.app1.dialog.StringDialog;
import fr.poseidonj.app1.entity.Product;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> stringAdapter;
    ArrayAdapter<Product> productArrayAdapter;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list);
        add = findViewById(R.id.btnAdd);
        //fillString();
        fillProduct();
    }

    private void fillString() {
        List<String> lst = new ArrayList<>(Arrays.asList("Hello", "World!", "How", "Are", "You"));

        stringAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lst);
        listView.setAdapter(stringAdapter);
        listView.setOnItemClickListener((adapterView, view, x, y) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmer la suppression").setMessage("Sure?")
                    .setPositiveButton(
                            "yes", (dialogInterface, i) -> {
                                stringAdapter.remove(stringAdapter.getItem(x));
                                stringAdapter.notifyDataSetChanged();
                                dialogInterface.dismiss();
                            })
                    .setNegativeButton("no", (dialogInterface, i) -> dialogInterface.cancel());
            builder.create().show();
        });

        add.setOnClickListener(view -> {
            StringDialog dialog = new StringDialog();
            dialog.show(getSupportFragmentManager(), "dialog1");
        });
    }

    private void fillProduct() {
        List<Product> lst = new ArrayList<>(Arrays.asList(new Product(1, "test1", 20), new Product(2, "test3", 20), new Product(3, "test2", 20)));
        productArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lst);
        listView.setAdapter(productArrayAdapter);
        listView.setOnItemClickListener((adapterView, view, x, y) -> {
            ProductDialog dialog = new ProductDialog(productArrayAdapter.getItem(x));
            dialog.show(getSupportFragmentManager(), "dialog1");
        });
        add.setOnClickListener(view -> {
            ProductDialog dialog = new ProductDialog(new Product());
            dialog.show(getSupportFragmentManager(), "dialog1");
        });

    }

    public void addStringElement(String toString) {
        stringAdapter.add(toString);
        stringAdapter.notifyDataSetChanged();
    }

    public void addProductElement(Product product) {
        productArrayAdapter.add(product);

        productArrayAdapter.notifyDataSetChanged();
    }

    public void deleteProductElement(Product product) {
        productArrayAdapter.remove(product);
        productArrayAdapter.notifyDataSetChanged();
    }
}