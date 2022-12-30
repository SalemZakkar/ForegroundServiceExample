package com.salem.background_service;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification {
    public static void initNotification(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "ForegroundTask";
            CharSequence name2 = "Main notifications";
            String description = "Foreground";
            String description2 = "Main";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("id", name, importance);
            NotificationChannel channel2 = new NotificationChannel("id2", name2, importance);
            channel.setDescription(description);
            channel2.setDescription(description2);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) getSystemService(context , NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            notificationManager.createNotificationChannel(channel2);
        }
    }
    public static void test(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "id2")
                .setSmallIcon(R.drawable.launch_background)
                .setContentTitle("Test")
                .setContentText("test")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("test:"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
        compat.notify(2 , builder.build());
    }
}
