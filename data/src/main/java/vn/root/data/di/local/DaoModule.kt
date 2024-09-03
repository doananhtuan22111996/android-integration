package vn.root.data.di.local

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.root.data.local.AppDatabase
import vn.root.data.local.ItemDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DaoModule {
	
	@Provides
	@Singleton
	fun provideItemDao(appDatabase: AppDatabase): ItemDao = appDatabase.itemDao()
}