package com.example.mynewsrecyclerview.ui.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewsrecyclerview.databinding.ActivityNotificationsBinding
import com.example.mynewsrecyclerview.ui.notification.AlarmReceiver
import java.util.*

class NotificationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationsBinding

    private lateinit var alarmManager: AlarmManager
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NOTIFICATION POP UP TIME SET UP TP 5PM:

        val alarmStartTime = Calendar.getInstance()!!
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 17)
        alarmStartTime.set(Calendar.MINUTE, 0)
        alarmStartTime.set(Calendar.SECOND, 0)

        // INITIATING ALARM MANAGER

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            Intent(applicationContext, AlarmReceiver::class.java),
            0
        )

        // SWITCHING NOTIFICATIONS ON AND OFF

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->

            when {
                isChecked -> {

                    Toast.makeText(
                        applicationContext,
                        "I'll get the articles you need and I will see you at 5PM.",
                        Toast.LENGTH_LONG
                    ).show()

                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        alarmStartTime.timeInMillis,
                        AlarmManager.INTERVAL_DAY,
                        alarmIntent
                    )

                    // CREATING VALUES TO BE STORED IN SHARED PREFERENCES

                    val searchQuery = binding.editTextQueryNotifications.text.toString()

                    val arts = if (binding.artsCB.isChecked) "arts" else ""
                    val business = if (binding.businessCB.isChecked) "business" else ""
                    val entrepreneurs = if (binding.entrepreneursCB.isChecked) "entrepreneurs" else ""
                    val politics = if (binding.politicsCB.isChecked) "politics" else ""
                    val sports = if (binding.sportsCB.isChecked) "sports" else ""
                    val travel = if (binding.travelCB.isChecked) "travel" else ""

                    val sharedPref = getSharedPreferences("NotificationsActivity", android.content.Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("arts", arts)
                    editor.putString("business", business)
                    editor.putString("entrepreneurs", entrepreneurs)
                    editor.putString("politics", politics)
                    editor.putString("sports", sports)
                    editor.putString("travel", travel)
                    editor.putString("searchQuery", searchQuery)
                    editor.apply()
                }
            }

            when {
                !isChecked -> {
                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        alarmStartTime.timeInMillis,
                        AlarmManager.INTERVAL_DAY,
                        alarmIntent
                    )

                    alarmManager.cancel(alarmIntent)


                    Toast.makeText(
                        applicationContext,
                        "Notifications cancelled.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }


    }


}
