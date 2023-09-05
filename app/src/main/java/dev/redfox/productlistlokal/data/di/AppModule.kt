package dev.redfox.productlistlokal.data.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.redfox.productlistlokal.data.utils.Constants.Companion.BASE_URL
import dev.redfox.productlistlokal.domain.retrofit.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    private var mClient: OkHttpClient? = null
    private val gson = GsonBuilder().setLenient().create()

    val client: OkHttpClient
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor { chain ->
                        val originalRequest = chain.request()
                        val newRequest = originalRequest.newBuilder()
                            .header("Content-Type", "application/json")
                            // Add other headers if needed
                            .build()
                        chain.proceed(newRequest)
                    }/// show all JSON in logCat
                mClient = httpBuilder.build()

            }
            return mClient!!
        }

    @Provides
    fun getRetrofitService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}