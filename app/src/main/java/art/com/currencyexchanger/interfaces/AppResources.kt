package art.com.currencyexchanger.interfaces

interface AppResources {
    fun getString(resId: Int): String
    fun getString(resId: Int, vararg formatArgs: Any): String
}