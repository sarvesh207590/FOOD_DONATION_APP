package com.example.project;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

public class NOTIFICATION  {

    private static final String CHANNEL_ID = "donation_channel";
    private static final String CHANNEL_NAME = "Donation Notifications";
    private static final String CHANNEL_DESC = "Notification for donation";

    public static void createNotification(Context context, String title, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            notificationManager.createNotificationChannel(channel);
        }

        Bitmap appLogo = BitmapFactory.decodeResource(context.getResources(), R.drawable.inner_notification_image); // Change app_logo to your app's logo

        Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Builder(context, CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.app_img) // Change notification_icon to your notification icon
                    .setLargeIcon(appLogo)
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .setAutoCancel(true);
        }

        notificationManager.notify(0, builder.build());
    }
}
