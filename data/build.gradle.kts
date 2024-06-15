import com.android.build.api.dsl.LibraryProductFlavor
import java.io.FileInputStream
import java.util.Properties

plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.ksp)
}

android {
	namespace = "vn.root.data"
	compileSdk = libs.versions.compileSdk.get().toInt()
	
	defaultConfig {
		minSdk = libs.versions.minSdk.get().toInt()
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}
	
	buildTypes {
		release {
			isJniDebuggable = false
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
			)
		}
	}
	
	flavorDimensions += "environment"
	productFlavors {
		fun loadConfig(flavor: LibraryProductFlavor) {
			println("Data ----- productFlavors: -----> ${flavor.name}")
			val appProperties = Properties().apply {
				load(FileInputStream(rootProject.file("env.${flavor.name}.properties")))
			}
			println("main.domain: -----> " + appProperties.getProperty("main.domain"))
			flavor.buildConfigField(
				"String", "MAIN_DOMAIN", appProperties.getProperty("main.domain")
			)
		}
		
		create("dev") {
			dimension = "environment"
			loadConfig(this)
		}
		
		create("prod") {
			dimension = "environment"
			loadConfig(this)
		}
	}
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	
	kotlinOptions {
		jvmTarget = "1.8"
	}
	
	buildFeatures {
		buildConfig = true
	}
}

dependencies {
	implementation(project(":domain"))
	implementation(libs.androidx.paging.common)
	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.security)
	implementation(libs.androidx.hilt)
	ksp(libs.androidx.hilt.compiler)
	implementation(libs.di.koin)
	implementation(libs.retrofit)
	implementation(libs.retrofit.gson)
	implementation(libs.logger.timber)
	implementation(libs.logger.okhttp)

	testImplementation(libs.androidx.coroutines.test)
	testImplementation(libs.junit)
	testImplementation(libs.mockito)
	testImplementation(libs.mockito.kotlin)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}
