package art.com.currencyexchanger.data

import android.content.res.Resources
import art.com.currencyexchanger.interfaces.AppResources

class ResourcesProvider(private val resources: Resources): AppResources {
    override fun getString(resId: Int) = resources.getString(resId)
    override fun getString(resId: Int, vararg formatArgs: Any) = resources.getString(resId, formatArgs)
}