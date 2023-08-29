package fr.poseidonj.app1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.poseidonj.app1.R;
import fr.poseidonj.app1.entity.Product;
import fr.poseidonj.app1.entity.ProductHolder;

public class CustomAdapter extends ArrayAdapter<Product> {
    protected List<Product> products;
    protected final Context context;

    public CustomAdapter(@NonNull Context context, int resource, List<Product> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ProductHolder holder;
        if (convertView == null) {
            LayoutInflater systemService = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = systemService.inflate(R.layout.product_layout, null);


            holder = new ProductHolder(convertView.findViewById(R.id.imageView), convertView.findViewById(R.id.desc));
        } else {
            holder = (ProductHolder) convertView.getTag();
        }

        holder.fill(products.get(position).getDesc(),R.drawable.caterium_ingot);
        return convertView;
    }


}
