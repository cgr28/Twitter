package com.codepath.apps.restclienttemplate

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeFormatter {

    companion object {

        val SECOND_MILLIS = 1000
        val MINUTE_MILLIS = 60 * SECOND_MILLIS
        val HOUR_MILLIS = 60 * MINUTE_MILLIS
        val DAY_MILLIS = 24 * HOUR_MILLIS

        fun getTimeDifference(rawJsonDate: String?): String? {
            val twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
            val sf = SimpleDateFormat(twitterFormat, Locale.ENGLISH)
            sf.setLenient(true)
            try {
                val time: Long = sf.parse(rawJsonDate).getTime()
                val now = System.currentTimeMillis()
                val diff = now - time
                return if (diff < MINUTE_MILLIS) {
                    "just now"
                } else if (diff < 2 * MINUTE_MILLIS) {
                    "a minute ago"
                } else if (diff < 50 * MINUTE_MILLIS) {
                    (diff / MINUTE_MILLIS).toString() + " m"
                } else if (diff < 90 * MINUTE_MILLIS) {
                    "an hour ago"
                } else if (diff < 24 * HOUR_MILLIS) {
                    (diff / HOUR_MILLIS).toString() + " h"
                } else if (diff < 48 * HOUR_MILLIS) {
                    "yesterday"
                } else {
                    (diff / DAY_MILLIS).toString() + " d"
                }
            } catch (e: ParseException) {
                Log.i("TimeFormatter", "getRelativeTimeAgo failed")
                e.printStackTrace()
            }
            return ""
        }
    }

}
