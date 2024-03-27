package vn.geekup.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.geekup.domain.usecase.AuthUseCase
import vn.geekup.domain.usecase.AuthUseCaseImplement
import vn.geekup.domain.usecase.MomentUseCase
import vn.geekup.domain.usecase.MomentUseCaseImplement
import vn.geekup.app.pages.login.LoginViewModel
import vn.geekup.app.pages.moment.MomentViewModel
import vn.geekup.app.pages.root.RootViewModel
import vn.geekup.app.network.NetworkChange

val applicationModules = module {
    single { NetworkChange(androidContext()) }
}

val useCaseModules = module(createdAtStart = true) {
    factory<AuthUseCase> { AuthUseCaseImplement(get()) }
    factory<MomentUseCase> { MomentUseCaseImplement(get()) }
}

val viewModelModules = module(createdAtStart = true) {
    viewModel { RootViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { MomentViewModel(get(), get()) }
}