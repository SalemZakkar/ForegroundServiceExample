package com.salem.background_service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class AppService extends Service {
    public AppService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "id2")
                .setSmallIcon(R.drawable.launch_background)
                .setContentTitle("Test")
                .setContentText("test")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("test:"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent);
        startForeground(2 , builder.build());
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}