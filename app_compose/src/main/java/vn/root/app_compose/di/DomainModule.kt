package vn.root.app_compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import vn.root.domain.repository.AuthRepository
import vn.root.domain.repository.PagingRepository
import vn.root.domain.usecase.LoginComposeUseCase
import vn.root.domain.usecase.LogoutComposeUseCase
import vn.root.domain.usecase.PagingComposeLocalUseCase
import vn.root.domain.usecase.PagingComposeNetworkUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
	
	@Provides
	fun provideLoginUseCase(repository: AuthRepository): LoginComposeUseCase =
		LoginComposeUseCase(repository = repository)
	
	@Provides
	fun provideLogoutUseCase(repository: AuthRepository): LogoutComposeUseCase =
		LogoutComposeUseCase(repository = repository)
	
	@Provides
	fun providePagingNetworkUseCase(repository: PagingRepository): PagingComposeNetworkUseCase =
		PagingComposeNetworkUseCase(repository = repository)
	
	@Provides
	fun providePagingLocalUseCase(repository: PagingRepository): PagingComposeLocalUseCase =
		PagingComposeLocalUseCase(repository = repository)
}