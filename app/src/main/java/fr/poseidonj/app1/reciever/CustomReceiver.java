package fr.poseidonj.app1.reciever;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("action")) {
            Toast.makeText(context, "receive custom bdr", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

        } else
            Toast.makeText(context, "receive", Toast.LENGTH_SHORT).show();
    }
}
