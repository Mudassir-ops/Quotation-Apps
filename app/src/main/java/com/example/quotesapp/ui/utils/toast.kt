package com.example.quotesapp.ui.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.quotesapp.R

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

fun Activity.shareThisApp(){
    try {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "text/plain"
        sendIntent.putExtra(
            Intent.EXTRA_SUBJECT,"ChargingAnimation")
        var shareMessage = "\n Let me recommend you this application\n\n"
        shareMessage = """
             ${shareMessage}https://play.google.com/store/apps/details?id= ${this.packageName}
        """.trimIndent()
        sendIntent.putExtra(Intent.EXTRA_TEXT,shareMessage)
        this.startActivity(Intent.createChooser(sendIntent, "Choose one"))
    }catch (e:java.lang.Exception){
        e.printStackTrace()
        this.toast("No Launcher")
    }
}

fun Activity.feedBackWithEmail(title:String,message:String,emailId:String){
    try {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.flags  = Intent.FLAG_ACTIVITY_CLEAR_TASK
        emailIntent.data  = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailId))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, title)
        emailIntent.putExtra(Intent.EXTRA_TEXT, message)
        this.startActivity(emailIntent)

    }catch (e:java.lang.Exception){
        e.printStackTrace()
    }
}

fun Activity.privacyPolicyUrl(){
    try {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(this.getString(R.string.privacy_policy_link)))
        )
    }catch (e:Exception){
        e.printStackTrace()
        toast(this.getString(R.string.no_launcher))

    }
}

fun Activity.moreApps(){
    try {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(this.getString(R.string.more_app_link)))
        )
    }catch (e:Exception){
        e.printStackTrace()
        toast(this.getString(R.string.no_launcher))

    }
}

fun Activity.rateUs(){
    try {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=${this.packageName}")
            )
        )

    }catch (e:Exception){
        e.printStackTrace()
        toast("No Launcher")
    }
}