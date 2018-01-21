package com.nyinyi.nw.sfc.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by User on 11/25/2017.
 */

public class InternetSateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo.isConnected())
        {
            Toast.makeText(context, "Your device is connect to internet."  , Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Your device is no longer connected to internet.", Toast.LENGTH_SHORT).show();
        }

    }
}
