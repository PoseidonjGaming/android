package fr.poseidonj.app1.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import fr.poseidonj.app1.ListActivity;
import fr.poseidonj.app1.R;
import fr.poseidonj.app1.entity.Product;

public class ProductDialog extends DialogFragment {
    private Product product;
    Button add, delete,cancel;
    Context context;
    EditText inputId, inputDesc, inputPrice;

    public ProductDialog(Product product) {
        this.product = product;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_dialog, viewGroup, false);

        inputId=view.findViewById(R.id.editTextNumber);
        inputDesc=view.findViewById(R.id.editTextDesc);
        inputPrice=view.findViewById(R.id.editTextNumberPrice);

        inputId.setText(String.valueOf(product.getId()));
        inputDesc.setText(product.getDesc());
        inputPrice.setText(String.valueOf(product.getPrice()));

        cancel= view.findViewById(R.id.buttonCancel);
        add= view.findViewById(R.id.buttonAdd);
        delete= view.findViewById(R.id.buttonDelete);


        add.setOnClickListener(view1 ->  {
            ((ListActivity) context).addProductElement(new Product(Integer.parseInt(inputId.getText().toString()), inputDesc.getText().toString(),Double.parseDouble(inputId.getText().toString())));
            Objects.requireNonNull(getDialog()).dismiss();
        });

        delete.setOnClickListener(view1 -> {
            ((ListActivity) context).deleteProductElement(product);
            Objects.requireNonNull(getDialog()).dismiss();
        });

        cancel.setOnClickListener(view1 -> {
            Objects.requireNonNull(getDialog()).dismiss();
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
