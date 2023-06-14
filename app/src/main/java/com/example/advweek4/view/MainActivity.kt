package com.example.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.advweek4.R
import com.example.advweek4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        private var instance:MainActivity ?= null
        	fun showNotification(title:String, content:String, icon:Int) {
                val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

                val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }
                val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext.applicationContext!!)

                // if(ActivityCompat.checkSelfPermission((instance!!.applicationContext, android.Manifest.permission.POST_NOTIFICATIONS))
                notificationManager.notify(1001, notificationBuilder.build())
            }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "App Notification Channel")
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                showNotification("Ini Nama Student", "A new notification created", R.drawable.ic_baseline_check_circle_24)
            }

//        val observable = Observable.just("satu", "dua","tiga")
//        val observer = object:Observer<String>{
//            override fun onSubscribe(d: Disposable?) {
//                Log.d("Messages", "begin subscribe")
//            }
//
//            override fun onNext(t: String?) {
//                Log.d("Messages", "data: $t")
//            }
//
//            override fun onError(e: Throwable?) {
//                Log.e("Messages", "error: ${e!!.message.toString()}")
//            }
//
//            override fun onComplete() {
//                Log.d("Messages", "complete")
//            }
//
//        }
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)

    }
}