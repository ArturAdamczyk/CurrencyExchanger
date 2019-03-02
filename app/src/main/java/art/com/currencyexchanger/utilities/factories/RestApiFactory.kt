package art.com.currencyexchanger.utilities.factories

import art.com.currencyexchanger.interfaces.RestApi
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber

class RestApiFactory {
    companion object{
        private val CLASS_TAG = RestApiFactory::class.java.simpleName

        @Throws(IllegalArgumentException::class)
        fun get(webServiceUrl: String, okHttpClient: OkHttpClient): RestApi {
            try {
                return Retrofit.Builder()
                    .baseUrl(webServiceUrl)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                        )
                    )
                    .build().create(RestApi::class.java)
            } catch (e: IllegalArgumentException) {
                Timber.d(CLASS_TAG, e.message)
                throw IllegalArgumentException(e.message)
            }
        }
    }
}