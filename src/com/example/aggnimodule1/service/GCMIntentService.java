package com.example.aggnimodule1.service;
 
import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.EventListActivity;
import com.example.aggnimodule1.receiver.GCMBroadcastReceiver;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
 
public class GCMIntentService extends IntentService {
 
   public static final int NOTIFICATION_ID = 1000;
   NotificationManager mNotificationManager;
   NotificationCompat.Builder builder;
 
   public GCMIntentService() {
      super(GCMIntentService.class.getName());
   }
 
   @Override
   protected void onHandleIntent(Intent intent) {
      Bundle extras = intent.getExtras();
 
      if (!extras.isEmpty()) {
 
         // read extras as sent from server
         String message = extras.getString("message");
         String serverTime = extras.getString("timestamp");
         sendNotification("Message: " + message + "\n" + "Server Time: "
               + serverTime);
      }
      // Release the wake lock provided by the WakefulBroadcastReceiver.
      GCMBroadcastReceiver.completeWakefulIntent(intent);
   }
 
   private void sendNotification(String msg) {
      mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
 
      PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
            new Intent(this, EventListActivity.class), 0);
 
      NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
            this).setSmallIcon(R.drawable.app_logo)
            .setContentTitle("Aggni, New Event!")
            .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
            .setContentText(msg);
 
      mBuilder.setContentIntent(contentIntent);
      mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
   }
 
}