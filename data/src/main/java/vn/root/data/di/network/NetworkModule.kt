package vn.root.data.di.network

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.root.data.Config
import vn.root.data.Config.mainDomain
import vn.root.data.di.qualifier.AnoHttpAuthenticatorInterceptor
import vn.root.data.di.qualifier.AnoHttpLoggingInterceptor
import vn.root.data.di.qualifier.AnoRetrofitApiService
import vn.root.data.network.HttpAuthenticatorInterceptor
import vn.root.data.local.PreferenceWrapper
import vn.root.data.network.NullOrEmptyConverterFactory
import vn.root.data.service.ApiService
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
	
	@Provides
	@Singleton
	@AnoRetrofitApiService
	fun provideApiServices(
		okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
	): ApiService {
		return provideRetrofit<ApiService>(
			okHttpClient = okHttpClient, gsonConverterFactory = gsonConverterFactory
		)
	}
	
	@Provides
	@Singleton
	fun provideCache(@ApplicationContext context: Context): Cache {
		val file = File(context.cacheDir, Config.Cache.CACHE_FILE_NAME)
		val isSuccess = file.mkdirs()
		return if (isSuccess) {
			Cache(file, Config.Cache.CACHE_FILE_SIZE)
		} else Cache(context.cacheDir, Config.Cache.CACHE_FILE_SIZE)
	}
	
	@Provides
	@Singleton
	@AnoHttpLoggingInterceptor
	fun bindHttpLoggingInterceptor(): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
	}
	
	@Provides
	@Singleton
	@AnoHttpAuthenticatorInterceptor
	fun bindHttpAuthenticatorInterceptor(preferenceWrapper: PreferenceWrapper): HttpAuthenticatorInterceptor {
		return HttpAuthenticatorInterceptor(preferenceWrapper)
	}
	
	@Provides
	@Singleton
	fun provideOkHttpClient(
		@AnoHttpLoggingInterceptor httpLoggingInterceptor: HttpLoggingInterceptor,
		@AnoHttpAuthenticatorInterceptor httpAuthenticatorInterceptor: HttpAuthenticatorInterceptor,
		preferenceWrapper: PreferenceWrapper,
		cache: Cache,
	): OkHttpClient {
		val builder =
			OkHttpClient.Builder().readTimeout(Config.TimeOut.TIMEOUT_READ_SECOND, TimeUnit.SECONDS)
				.connectTimeout(Config.TimeOut.TIMEOUT_CONNECT_SECOND, TimeUnit.SECONDS)
				.writeTimeout(Config.TimeOut.TIMEOUT_WRITE_SECOND, TimeUnit.SECONDS)
				.addNetworkInterceptor(
					Interceptor { chain ->
						var request = chain.request()
						val builder = request.newBuilder()
						val token =
							preferenceWrapper.getString(Config.SharePreference.KEY_AUTH_TOKEN, "")
						if (token.isNotEmpty()) {
							builder.addHeader("Authorization", "Bearer $token")
						}
						request = builder.build()
						chain.proceed(request)
					},
				).authenticator(httpAuthenticatorInterceptor)
		if (Config.isDebug) {
			builder.addInterceptor(httpLoggingInterceptor)
		} else {
			builder.cache(cache)
		}
		return builder.build()
	}
	
	@Provides
	@Singleton
	fun provideGsonConverterFactory(): GsonConverterFactory {
		return GsonConverterFactory.create()
	}
	
	private inline fun <reified T> provideRetrofit(
		okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
	): T {
		val retrofit = Retrofit.Builder().baseUrl(mainDomain).client(okHttpClient)
			.addConverterFactory(NullOrEmptyConverterFactory())
			.addConverterFactory(gsonConverterFactory).build()
		return retrofit.create(T::class.java)
	}
}