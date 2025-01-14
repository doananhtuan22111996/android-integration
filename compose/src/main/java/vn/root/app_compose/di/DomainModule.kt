package vn.root.app_compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import vn.root.domain.repository.AuthRepository
import vn.root.domain.repository.PagingRepository
import vn.root.domain.usecase.LoginUseCase
import vn.root.domain.usecase.PagingLocalUseCase
import vn.root.domain.usecase.PagingNetworkUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase = LoginUseCase(repository = repository)

    @Provides
    fun providePagingNetworkUseCase(repository: PagingRepository): PagingNetworkUseCase = PagingNetworkUseCase(repository = repository)

    @Provides
    fun providePagingLocalUseCase(repository: PagingRepository): PagingLocalUseCase = PagingLocalUseCase(repository = repository)
}
