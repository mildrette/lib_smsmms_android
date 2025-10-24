package com.afkanerd.smswithoutborders_libsmsmms.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.afkanerd.smswithoutborders_libsmsmms.data.SmsWorkManager
import com.afkanerd.smswithoutborders_libsmsmms.services.ImageTransmissionService

class NotificationActionImpl: BroadcastReceiver() {
    companion object {
        const val NOTIFICATION_STOP_ACTION_INTENT_ACTION = "NOTIFICATION_STOP_ACTION_INTENT_ACTION"
        const val NOTIFICATION_RETRY_ACTION_INTENT_ACTION = "NOTIFICATION_RETRY_ACTION_INTENT_ACTION"
        const val NOTIFICATION_PAUSE_ACTION_INTENT_ACTION = "NOTIFICATION_PAUSE_ACTION_INTENT_ACTION"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent == null) return

        when(intent.action) {
            NOTIFICATION_STOP_ACTION_INTENT_ACTION -> {
                context?.sendBroadcast(Intent(SmsWorkManager.ITP_SERVICE_COMPLETION))
            }
            NOTIFICATION_PAUSE_ACTION_INTENT_ACTION -> {
            }
            NOTIFICATION_RETRY_ACTION_INTENT_ACTION -> {
                context?.sendBroadcast(Intent(SmsWorkManager.ITP_RETRY_SERVICE))
            }
        }
    }
}