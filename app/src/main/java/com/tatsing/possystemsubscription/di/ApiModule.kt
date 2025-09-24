package com.tatsing.possystemsubscription.di

import android.content.Context
import com.tatsing.possystemsubscription.BuildConfig
import com.tatsing.possystemsubscription.data.services.SignInService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
	
	@Singleton
	@Provides
	fun provideHttpInterceptor(): HttpLoggingInterceptor {
		val loggingInterceptor = HttpLoggingInterceptor()
		if (BuildConfig.DEBUG) {
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
		}
		return loggingInterceptor
	}
	
	@Singleton
	@Provides
	fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
	
	@Singleton
	@Provides
	fun provideOkHttpClient(
		loggingInterceptor: HttpLoggingInterceptor,
		@ApplicationContext context: Context
	): OkHttpClient {

		// Trust all certificates (not recommended for production)
		val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
			override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
			override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
			override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
		})

		val sslContext = SSLContext.getInstance("SSL").apply {
			init(null, trustAllCerts, java.security.SecureRandom())
		}

		val trustManager = trustAllCerts[0] as X509TrustManager
		
		val interceptor = Interceptor { chain ->
			val request = chain.request()
				.newBuilder()
				.addHeader("Accept", "application/json")
				.addHeader("apikey", BuildConfig.API_KEY_ANON)
				.build()
			chain.proceed(request)
		}
		
		return OkHttpClient.Builder()
			.sslSocketFactory(sslContext.socketFactory, trustManager)
			.addInterceptor(interceptor)
			.addInterceptor(loggingInterceptor)
			.connectTimeout(Constants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
			.readTimeout(Constants.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
			.writeTimeout(Constants.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
			.build()
	}
	
	@Singleton
	@Provides
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
		gsonConverterFactory: GsonConverterFactory
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.HOST)
			.client(okHttpClient)
			.addConverterFactory(gsonConverterFactory)
			.build()
	}
	
	@Singleton
	@Provides
	fun provideSignInApi(retrofit: Retrofit): SignInService =
		retrofit.create(SignInService::class.java)

}