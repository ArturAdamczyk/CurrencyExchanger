package art.com.currencyexchanger.utilities

import android.view.View
import art.com.currencyexchanger.interfaces.Message
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class Messenger: Message {

    override fun logMessage(classTag: String, logMsg: String): Messenger {
        Timber.e(logMsg)
        return this
    }

    override fun showMessage(appMsg: String, view: View): Messenger {
        Snackbar.make(view, appMsg, Snackbar.LENGTH_LONG).show()
        return this
    }

}