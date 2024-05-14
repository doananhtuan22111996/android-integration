package vn.root.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.root.app.pages.workflow.login.LoginViewModel
import vn.root.app.pages.root.RootViewModel
import vn.root.app.network.NetworkChange
import vn.root.app.pages.workflow.right.RightViewModel
import vn.root.app.pages.workflow.home.HomeViewModel
import vn.root.app.pages.workflow.left.LeftViewModel

internal object AppModules {
	val applicationModules = module(createdAtStart = true) {
		single { NetworkChange(androidContext()) }
	}
	
	val viewModelModules = module(createdAtStart = true) {
		viewModel { RootViewModel() }
		viewModel { LoginViewModel(get(), get()) }
		viewModel { HomeViewModel() }
		viewModel { LeftViewModel(get()) }
		viewModel { RightViewModel(get()) }
	}
}
