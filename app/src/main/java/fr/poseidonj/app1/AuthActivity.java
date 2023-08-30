package fr.poseidonj.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class AuthActivity extends AppCompatActivity {

    Button btnStorage, btnCam;

    private static final int CAM_PERMISSION = 100;
    private static final int STORAGE_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnCam = findViewById(R.id.btnCam);
        btnStorage = findViewById(R.id.btnStorage);

        btnStorage.setOnClickListener(v -> {
            checkPermission(Manifest.permission.READ_MEDIA_IMAGES, STORAGE_PERMISSION);
        });
        btnCam.setOnClickListener(v -> {
            checkPermission(Manifest.permission.CAMERA, CAM_PERMISSION);
        });
    }

    private void checkPermission(String permission, int permissionCode) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, permissionCode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "permission accorded", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == CAM_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "permission accorded", Toast.LENGTH_SHORT).show();
            }
        }
    }
}