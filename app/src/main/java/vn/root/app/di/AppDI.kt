package vn.root.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.root.app.pages.login.LoginViewModel
import vn.root.app.pages.root.RootViewModel
import vn.root.app.network.NetworkChange
import vn.root.app.pages.homeLocal.HomeLocalViewModel
import vn.root.app.pages.home.HomeViewModel
import vn.root.app.pages.main.MainViewModel

internal object AppModules {
    val applicationModules = module(createdAtStart = true) {
        single { NetworkChange(androidContext()) }
    }

    val viewModelModules = module(createdAtStart = true) {
        viewModel { RootViewModel() }
        viewModel { LoginViewModel(get(), get()) }
        viewModel { MainViewModel() }
        viewModel { HomeViewModel(get()) }
        viewModel { HomeLocalViewModel(get()) }
    }
}
