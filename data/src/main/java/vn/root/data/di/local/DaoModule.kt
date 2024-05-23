package vn.root.data.di.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.root.data.local.dao.ItemDao
import vn.root.data.local.dao.ItemDao_Impl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DaoModule {
	
	@Binds
	@Singleton
	abstract fun bindItemDao(itemDaoImpl: ItemDao_Impl): ItemDao
}