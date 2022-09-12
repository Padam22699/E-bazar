package com.example.e_bazar.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.EventLogTags
import android.util.Log
import com.example.e_bazar.DeshBoardActivity
import com.example.e_bazar.Fragments.Orderd_Fragment
import com.example.e_bazar.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class firebase_push_notification: FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

     if(message.notification != null) pushNotification(
         message.notification?.title,
         message.notification?.body
     )

    }

    private fun pushNotification(title:String?, Msg: String?) {
        val nm:NotificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notification:Notification
       val CHANNEL_ID="push notification"
        val notifyintent=Intent(this,Orderd_Fragment::class.java)
        notifyintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val Pi:PendingIntent=PendingIntent.getActivity(this,100,notifyintent,PendingIntent.FLAG_UPDATE_CURRENT)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val Name:CharSequence="Custom Channel"
            val Description:String="Channel for push Notification"
            val importance:Int=NotificationManager.IMPORTANCE_DEFAULT
            val channel:NotificationChannel= NotificationChannel(CHANNEL_ID,Name,importance)
            channel.description=Description

            if(nm!=null){
                nm.createNotificationChannel(channel)
            }
            notification=Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentIntent(Pi)
                .setSubText(Msg)
                .setContentTitle(title)
                .setChannelId(CHANNEL_ID)
                .setAutoCancel(true)
                .build()
        }else{
            notification=Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentIntent(Pi)
                .setSubText(Msg)
                .setContentTitle(title)
                .setAutoCancel(true)
                .build()
        }
        nm.notify(1,notification)

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("refreshedToken",token)
    }
}