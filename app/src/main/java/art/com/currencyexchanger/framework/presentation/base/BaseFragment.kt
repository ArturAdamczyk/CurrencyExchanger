package art.com.currencyexchanger.framework.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import art.com.currencyexchanger.interfaces.AppResources
import art.com.currencyexchanger.interfaces.Message
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass

abstract class BaseFragment<out T : BaseViewModel>(viewModelClass: KClass<T>) : Fragment() {
    private val CLASS_TAG = this.javaClass.name
    protected val messenger: Message by inject()
    protected val appResources: AppResources by inject()
    protected val viewModel: T by viewModelByClass(viewModelClass)
    abstract val layoutId: Int
    protected lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    open fun logMessage(msg: String) {
        messenger.logMessage(CLASS_TAG, msg)
    }

    fun showMessage(msg: String) {
        messenger.showMessage(msg, this.rootView)
    }

    fun passMessage(logMsg: String, userCommunicate: String) {
        messenger.logMessage(CLASS_TAG, logMsg)
            .showMessage(userCommunicate, this.rootView)
    }

    fun passMessage(msg: String) {
        messenger.logMessage(CLASS_TAG, msg)
            .showMessage(msg, this.rootView)
    }


    override fun onStop() {
        with(viewModel) {
            passMessage.removeObservers(this@BaseFragment)
            showMessage.removeObservers(this@BaseFragment)
            logMessage.removeObservers(this@BaseFragment)
            clearPassMessage()
            clearShowMessage()
            clearLogMessage()
        }
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            showMessage.observe(this@BaseFragment, Observer {
                if (it != null) {
                    showMessage(it)
                    viewModel.clearShowMessage()
                }
            })
            logMessage.observe(this@BaseFragment, Observer {
                if (it != null) {
                    logMessage(it)
                    viewModel.clearLogMessage()
                }
            })
            passMessage.observe(this@BaseFragment, Observer {
                if (it != null) {
                    passMessage(it)
                    viewModel.clearPassMessage()
                }
            })
        }

    }
}