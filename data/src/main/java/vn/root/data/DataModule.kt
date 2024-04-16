package vn.root.data

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory
import vn.root.data.local.AppDatabase
import vn.root.data.local.PreferenceWrapper
import vn.root.data.network.TokenAuthenticator
import vn.root.data.repository.AuthRepositoryImpl
import vn.root.data.repository.PagingRepositoryImpl
import vn.root.data.service.ApiService
import vn.root.domain.repository.AuthRepository
import vn.root.domain.repository.PagingRepository

object DataModule {
    val localModules = module(createdAtStart = true) {
        single { GsonConverterFactory.create() }
        single { PreferenceWrapper(androidContext()) }
        single { AppDatabase.getInstance(androidContext()) }
        single { get<AppDatabase>().itemDao() }
    }

    val remoteModules = module(createdAtStart = true) {
        single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
        single { TokenAuthenticator(get()) }
        single {
            Providers.provideOkHttpClient(
                get(), get(), get(), Providers.provideCache(androidContext())
            )
        }
        single { Providers.provideRetrofit<ApiService>(get(), get()) }
    }

    var repositoryModules = module(createdAtStart = true) {
        factory<AuthRepository> {
            AuthRepositoryImpl(get(), get())
        }

        factory<PagingRepository> {
            PagingRepositoryImpl(get(), get())
        }
    }
}
