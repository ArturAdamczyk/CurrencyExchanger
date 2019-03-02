package art.com.currencyexchanger.framework.presentation.features.currency_rate

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import art.com.currencyexchanger.R
import art.com.currencyexchanger.framework.presentation.base.BaseViewModel
import art.com.currencyexchanger.interfaces.AppResources
import art.com.currencyexchanger.interfaces.Repository
import art.com.currencyexchanger.models.domain.CurrencyRate
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy


class CurrencyRateViewModel(
    app: Application,
    private val repository: Repository,
    private val resources: AppResources
): BaseViewModel(app) {

    private val _currencyRate = MutableLiveData<CurrencyRate>()
    val currencyRate: LiveData<CurrencyRate> by lazy { _currencyRate }

    private lateinit var currencyId: String
    var lastDrawableId: Int = 0
    var previousLastValue: Float = 0F

    fun setCurrencyId(currencyId: String){ this.currencyId = currencyId }

    fun getCurrencyRate(){
        disposables += repository.getCurrencyRate(currencyId)
            .subscribeBy(
                onSuccess = { response -> _currencyRate.postValue(response) },
                onError = { passMessage(resources.getString(R.string.currency_rate_api_error)) }
            )
    }

    fun unsubscribeRefreshService(){
        repository.unsubscribeToCurrencyRate(currencyId)
            .subscribeBy(
                onSuccess = { logMessage(resources.getString(R.string.ticker_unsubscribe_success)) },
                onError = { logMessage(resources.getString(R.string.ticker_unsubscribe_failure))  }
            )
    }

    fun startRefreshService(){
        repository.getCurrencyRateSubscription()
            .subscribeBy(
                onNext = { response -> _currencyRate.postValue(response) },
                onError = { logMessage(resources.getString(R.string.ticker_subscription_obtain_failure))  }
            )
    }

    fun subscribeRefreshService(){
            repository.subscribeToCurrencyRate(currencyId)
                .subscribeBy(
                    onSuccess = { response ->
                        startRefreshService()
                        logMessage(resources.getString(R.string.ticker_subscribe_success))
                    },
                    onError = {
                        startRefreshService()
                        logMessage(resources.getString(R.string.ticker_subscribe_success))
                    }
                )

        }
}
