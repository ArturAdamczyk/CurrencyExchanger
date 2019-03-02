package art.com.currencyexchanger.framework

import android.app.Application
import art.com.currencyexchanger.data.RepositoryProvider
import art.com.currencyexchanger.data.ResourcesProvider
import art.com.currencyexchanger.framework.presentation.features.currency_pairs.CurrencyPairsViewModel
import art.com.currencyexchanger.framework.presentation.features.currency_rate.CurrencyRateViewModel
import art.com.currencyexchanger.interfaces.*
import art.com.currencyexchanger.utilities.WebSocketRequestBuilder
import art.com.currencyexchanger.utilities.AppMessenger
import art.com.currencyexchanger.utilities.Messenger
import art.com.currencyexchanger.utilities.WebSocketClient
import art.com.currencyexchanger.utilities.WebSocketFactory
import art.com.currencyexchanger.utilities.factories.OkHttpFactory
import art.com.currencyexchanger.utilities.factories.RestApiFactory
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import timber.log.Timber

class App: Application() {
    private val webSocketClient: WebSocketApi by inject()
    private val webSocketRequestBuilder: WebSocketRequestBuilder by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(dataModule, webModule, viewModelsModule, utilsModule))
        Timber.plant(Timber.DebugTree())
        webSocketClient
            .start(webSocketRequestBuilder.buildWebSocketStartRequest(AppConfig.WEB_SOCKET_ADDRESS))
    }

    private val dataModule = module {
        single<Repository> { RepositoryProvider(get(), get(), get()) }
        single<AppResources> { ResourcesProvider(this@App.resources) }
    }

    private val webModule = module {
        single { RestApiFactory.get(AppConfig.WEB_SERVICE_ADDRESS, get()) }
        single { OkHttpFactory.get() }
        single { WebSocketFactory() }
        single<WebSocketApi> { WebSocketClient(get(), get()) }
        single { WebSocketRequestBuilder() }
    }

    private val viewModelsModule = module {
        viewModel { CurrencyPairsViewModel(this@App, get()) }
        viewModel { CurrencyRateViewModel(this@App, get(), get()) }
    }

    private val utilsModule = module {
        single { Timber.DebugTree() }
        single<AppMessage> { AppMessenger() }
        single<Message> { Messenger() }
    }
}