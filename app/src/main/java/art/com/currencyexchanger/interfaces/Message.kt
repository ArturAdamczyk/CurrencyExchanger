package art.com.currencyexchanger.interfaces

import android.view.View

interface Message {
    fun logMessage(classTag: String, logMsg: String): Message
    fun showMessage(appMsg: String, view: View): Message
}