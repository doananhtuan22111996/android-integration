package vn.root.app.application

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppApplication : Application() {
	companion object {
		private val instanceLock = Any()
		var instance: AppApplication? = null
			set(value) {
				synchronized(instanceLock) {
					field = value
				}
			}
	}
	
	private val defaultLifecycleObserver = object : DefaultLifecycleObserver {
		
		override fun onStart(owner: LifecycleOwner) {
			super.onStart(owner)
			//TODO your code here
		}
		
		override fun onStop(owner: LifecycleOwner) {
			super.onStop(owner)
			//TODO your code here
		}
	}
	
	override fun onCreate() {
		super.onCreate()
		instance = this
		setupTimber()
		ProcessLifecycleOwner.get().lifecycle.addObserver(defaultLifecycleObserver)
	}
	
	private fun setupTimber() {
		Timber.plant(Timber.DebugTree())
	}
}