package art.com.currencyexchanger

import art.com.currencyexchanger.data.RepositoryProvider
import art.com.currencyexchanger.interfaces.Repository
import art.com.currencyexchanger.interfaces.RestApi

import io.reactivex.Single
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import art.com.currencyexchanger.interfaces.WebSocketApi
import art.com.currencyexchanger.utilities.WebSocketRequestBuilder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@RunWith(JUnit4::class)
class RepositoryTest {
    private lateinit var repository: Repository
    private val restApi = mock<RestApi>()
    private val webSocketClient = mock<WebSocketApi>()
    private val webSocketRequestBuilder = mock<WebSocketRequestBuilder>()
    private lateinit var testDataSets: TestDataSets

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        repository = RepositoryProvider(restApi, webSocketClient, webSocketRequestBuilder)
        testDataSets = TestDataSets()
    }

    @Test
    fun test_getCurrencyPairs_pass() {
        val response = testDataSets.getCurrencyPairsResponseSet()
        whenever(restApi.getCurrencyPairs()).thenReturn(Single.just(response))

        val expectedList = testDataSets.getCurrencyPairSet()

        repository.getCurrencyPairs()
            .test()
            .assertValue(expectedList)
        verify(restApi).getCurrencyPairs()
    }

    @Test
    fun test_getCurrencyRate_pass() {
        val response = testDataSets.getCurrencyRateResponseSet()
        whenever(restApi.getCurrencyRate("BTCUSD")).thenReturn(Single.just(response))

        val expectedList = testDataSets.getCurrencyRateSet("1")

        repository.getCurrencyRate("BTCUSD")
            .test()
            .assertValue(expectedList)
        verify(restApi).getCurrencyRate("BTCUSD")
    }

    @Test
    fun test_getCurrencyRate_fail() {
        val response = testDataSets.getCurrencyRateResponseSet()
        whenever(restApi.getCurrencyRate("BTCUSD")).thenReturn(Single.just(response))

        val expectedList = testDataSets.getCurrencyRateSet("2")

        repository.getCurrencyRate("BTCUSD")
            .test()
            .assertNever(expectedList)
        verify(restApi).getCurrencyRate("BTCUSD")
    }


}

class RxImmediateSchedulerRule : TestRule {

    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}