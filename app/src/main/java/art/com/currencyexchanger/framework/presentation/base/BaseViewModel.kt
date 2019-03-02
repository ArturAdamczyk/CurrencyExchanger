package art.com.currencyexchanger.framework.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    app: Application,
    protected val disposables: CompositeDisposable = CompositeDisposable()
    ) : AndroidViewModel(app) {

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String> = _showMessage

    private val _passMessage = MutableLiveData<String>()
    val passMessage: LiveData<String> = _passMessage

    private val _logMessage = MutableLiveData<String>()
    val logMessage: LiveData<String> = _logMessage


    protected fun passMessage(msg: String) {
        _passMessage.postValue(msg)
    }

    protected fun showMessage(msg: String) {
        _showMessage.postValue(msg)
    }

    protected fun logMessage(msg: String) {
        _logMessage.postValue(msg)
    }

    fun clearPassMessage(){
        _passMessage.value = null
    }

    fun clearShowMessage(){
        _showMessage.value = null
    }

    fun clearLogMessage(){
        _logMessage.value = null
    }

    public override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}