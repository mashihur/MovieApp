package com.miu.movieapp.other

import android.content.Context
import android.widget.Toast

class Helpers {
    companion object {
        const val INTENT_VALUE1  = "value1"
    }
}
fun Context?.toastShort(msg:String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context?.toastLong(msg:String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()