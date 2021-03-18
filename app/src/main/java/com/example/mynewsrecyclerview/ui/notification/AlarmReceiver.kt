package com.example.mynewsrecyclerview.ui.notification

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.mynewsrecyclerview.R
import com.example.mynewsrecyclerview.ui.activities.NotificationResult

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val ID = "CHANNEL_ID"
        const val channelName = "CHANNEL_NAME"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val searchActivityIntent = Intent(context, NotificationResult::class.java)


        val pendingMainActivityIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(searchActivityIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val channelNotification = NotificationCompat.Builder(context, ID)
                .setContentTitle("MyNews")
                .setContentText("Your News are ready. Click here to read them.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_top_stories)
                .setContentIntent(pendingMainActivityIntent)
                .setAutoCancel(true)

        val channel = NotificationChannel (ID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH)


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(channel)
            manager.notify(1, channelNotification.build())
        }




    }


}