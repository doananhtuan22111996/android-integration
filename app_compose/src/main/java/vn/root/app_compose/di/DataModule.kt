package vn.root.app_compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import vn.root.domain.repository.AuthRepository
import vn.root.data.repository.AuthRepositoryImpl
import vn.root.data.repository.PagingRepositoryImpl
import vn.root.domain.repository.PagingRepository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
	
	@Binds
	abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
	
	@Binds
	abstract fun bindPagingRepository(pagingRepositoryImpl: PagingRepositoryImpl): PagingRepository
}