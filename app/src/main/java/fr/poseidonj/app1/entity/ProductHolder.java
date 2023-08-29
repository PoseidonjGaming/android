package fr.poseidonj.app1.entity;

import android.media.Image;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import fr.poseidonj.app1.R;

public class ProductHolder {
    public ImageView imageViewPP;
    public TextView textViewDesc;

    public ProductHolder(ImageView imageViewPP, TextView textViewDesc) {
        this.imageViewPP = imageViewPP;
        this.textViewDesc = textViewDesc;
    }

    public void fill(String desc, int resource){
        textViewDesc.setText(desc);
        imageViewPP.setImageResource(resource);
    }

    public ImageView getImageViewPP() {
        return imageViewPP;
    }

    public void setImageViewPP(ImageView imageViewPP) {
        this.imageViewPP = imageViewPP;
    }

    public TextView getTextViewDesc() {
        return textViewDesc;
    }

    public void setTextViewDesc(TextView textViewDesc) {
        this.textViewDesc = textViewDesc;
    }
}
