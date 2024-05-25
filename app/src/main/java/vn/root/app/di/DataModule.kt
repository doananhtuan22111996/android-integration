package vn.root.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import vn.root.data.repository.AuthRepositoryImpl
import vn.root.data.repository.PagingRepositoryImpl
import vn.root.domain.repository.AuthRepository
import vn.root.domain.repository.PagingRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
	
	@Binds
	abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
	
	@Binds
	abstract fun bindPagingRepository(impl: PagingRepositoryImpl): PagingRepository
}