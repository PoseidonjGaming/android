package fr.poseidonj.app1.dialog;

import android.app.Dialog;
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

public class StringDialog extends DialogFragment {
    EditText input;
    Button add;
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_to_list, viewGroup, false);

        input = view.findViewById(R.id.inputName);
        add = view.findViewById(R.id.button);

        add.setOnClickListener(view1 -> {
            ((ListActivity) context).addStringElement(input.getText().toString());
            Objects.requireNonNull(getDialog()).dismiss();
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
