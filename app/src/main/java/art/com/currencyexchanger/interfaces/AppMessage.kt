package art.com.currencyexchanger.interfaces

interface AppMessage {
    fun logMessage(classTag: String, logMsg: String): AppMessage
}