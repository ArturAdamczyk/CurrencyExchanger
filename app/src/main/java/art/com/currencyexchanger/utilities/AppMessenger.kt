package art.com.currencyexchanger.utilities

import art.com.currencyexchanger.interfaces.AppMessage
import timber.log.Timber

class AppMessenger : AppMessage {

    override fun logMessage(classTag: String, logMsg: String): AppMessenger {
        Timber.tag(classTag).d(logMsg)
        return this
    }
}