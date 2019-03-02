package art.com.currencyexchanger.framework.presentation.features.currency_pairs

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import art.com.currencyexchanger.framework.presentation.base.BaseViewModel
import art.com.currencyexchanger.interfaces.Repository
import art.com.currencyexchanger.models.domain.CurrencyPair
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class CurrencyPairsViewModel(
    app: Application,
    private val repository: Repository
): BaseViewModel(app) {

    private val _currencyPairs = MutableLiveData<List<CurrencyPair>>()
    val currencyPairs: LiveData<List<CurrencyPair>> by lazy { _currencyPairs }

    private val _fetchError = MutableLiveData<Boolean>()
    val fetchError: LiveData<Boolean> by lazy { _fetchError }

    fun getCurrencyPairs(){
        disposables += repository.getCurrencyPairs()
            .subscribeBy(
                onSuccess = { response -> _currencyPairs.postValue(response) },
                onError = { _fetchError.postValue(true) }
            )
    }

}