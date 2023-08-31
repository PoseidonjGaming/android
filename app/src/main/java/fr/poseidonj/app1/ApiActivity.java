package fr.poseidonj.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import fr.poseidonj.app1.entity.User;

public class ApiActivity extends AppCompatActivity {

    ListView lstUser;
    Button btnCallApi;

    ArrayAdapter<User> adapter;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        lstUser=findViewById(R.id.lstApi);
        btnCallApi=findViewById(R.id.btnCallApi);

        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);

        btnCallApi.setOnClickListener(v -> {
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            JsonArrayRequest json=new JsonArrayRequest(Request.Method.GET, "http://127.0.0.1:8081/user/list", null,response -> {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        users.add(new User(jsonObject.getInt("id"), jsonObject.getString("username"), jsonObject.getString("role"),jsonObject.getString("password")));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            },error -> {
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
            });

            requestQueue.add(json);

        });
    }
}