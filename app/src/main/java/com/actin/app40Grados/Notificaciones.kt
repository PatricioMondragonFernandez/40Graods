package com.actin.app40Grados
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import app40grados.R

const val notificacionId = 1
const val channelId = "channel1"
const val titleExtra  = "titleExtra"
const val messageExtra = "messageExtra"


class Notificaciones: BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {

        val notificacion = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra)).build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(notificacionId, notificacion)

    }

}