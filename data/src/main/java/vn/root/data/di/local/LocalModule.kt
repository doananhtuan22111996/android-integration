package vn.root.data.di.local

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vn.root.data.local.AppDatabase
import vn.root.data.local.PreferenceWrapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {
	
	@Provides
	@Singleton
	fun providePreferenceWrapper(@ApplicationContext androidContext: Context): PreferenceWrapper =
		PreferenceWrapper(context = androidContext)
	
	@Provides
	@Singleton
	fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
		AppDatabase.getInstance(context = context)
}
