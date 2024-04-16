package vn.root.app.di

import org.koin.dsl.module
import vn.root.domain.usecase.LoginUseCase
import vn.root.domain.usecase.LoginUseCaseImpl
import vn.root.domain.usecase.LogoutUseCase
import vn.root.domain.usecase.LogoutUseCaseImpl
import vn.root.domain.usecase.PagingLocalUseCase
import vn.root.domain.usecase.PagingLocalUseCaseImpl
import vn.root.domain.usecase.PagingNetworkUseCase
import vn.root.domain.usecase.PagingNetworkUseCaseImpl

internal object DomainModules {
    val useCaseModules = module(createdAtStart = true) {
        factory<LoginUseCase> { LoginUseCaseImpl(get()) }
        factory<LogoutUseCase> { LogoutUseCaseImpl(get()) }
        factory<PagingNetworkUseCase> { PagingNetworkUseCaseImpl(get()) }
        factory<PagingLocalUseCase> { PagingLocalUseCaseImpl(get()) }
    }
}
