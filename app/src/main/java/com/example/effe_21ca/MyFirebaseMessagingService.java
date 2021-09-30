//package com.example.effe_21ca;
//
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.media.RingtoneManager;
//
//import androidx.core.app.NotificationCompat;
//
//import com.google.firebase.messaging.RemoteMessage;
//
//public class MyFirebaseMessagingService {
//
//    @Override
//    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        sendNotification(remoteMessage);
//    }
//    private void sendNotification(RemoteMessage remoteMessage) {
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent =PendingIntent.getActivity(this,
//                m,intent,PendingIntent.FLAG_ONE_SHOT);
//        String channelId =    getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder;
//        notificationBuilder =
//                new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.geekhaven_transparent)
//                        .setContentTitle(remoteMessage.getNotification.getTitle)
//                        .setContentText(remoteMessage.getNotification.getBody)
//                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//// Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//        notificationManager.notify(m, notificationBuilder.build());
//
//}
