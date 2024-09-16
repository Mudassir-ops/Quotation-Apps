package com.example.quotesapp.ui.utils

import android.app.Activity
import android.widget.Toast

private var toast: Toast? = null
fun Activity.toast(message: String) {
    try {
        if (this.isDestroyed || this.isFinishing) return
        if (toast != null) {
            toast?.cancel()
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        if (this.isDestroyed || this.isFinishing) return
        toast?.show()

    } catch (e: Exception) {
        e.printStackTrace()
    }
}