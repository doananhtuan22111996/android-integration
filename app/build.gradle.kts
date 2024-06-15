import com.android.build.api.dsl.ApplicationProductFlavor
import java.io.FileInputStream
import java.util.Properties
import java.util.regex.Matcher
import java.util.regex.Pattern

plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.androidHilt)
	id("org.jetbrains.kotlin.kapt")
	alias(libs.plugins.ksp)
	alias(libs.plugins.googleService)
	alias(libs.plugins.firebaseCrashlytics)
}

val getCurrentFlavor = {
	val tskReqStr: String = gradle.startParameter.taskRequests.toString()
	println("Gradle task: -----> $tskReqStr")
	val pattern = if (tskReqStr.contains("assemble")) {
		Pattern.compile("assemble(\\w+)(Release|Debug)")
	} else if (tskReqStr.contains("bundle")) {
		Pattern.compile("bundle(\\w+)(Release|Debug)")
	} else {
		Pattern.compile("generate(\\w+)(Release|Debug)")
	}
	val matcher: Matcher = pattern.matcher(tskReqStr)
	if (matcher.find()) {
		println("MATCHED -------> ${matcher.group(1).lowercase()}")
		matcher.group(1).lowercase()
	} else {
		println("NO MATCH FOUND ------> Return default flavor")
		"dev"
	}
}

android {
	namespace = "vn.root.app"
	compileSdk = libs.versions.compileSdk.get().toInt()
	
	defaultConfig {
		minSdk = libs.versions.minSdk.get().toInt()
		targetSdk = libs.versions.targetSdk.get().toInt()
		versionCode = libs.versions.versionCode.get().toInt()
		versionName = libs.versions.versionName.get()
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	signingConfigs {
		create(getCurrentFlavor()) {
			val appProperties = Properties().apply {
				load(FileInputStream(rootProject.file("env.${this@create.name}.properties")))
			}
			storeFile = file(appProperties.getProperty("keystore.path"))
			storePassword = appProperties.getProperty("keystore.pass")
			keyAlias = appProperties.getProperty("keystore.alias")
			keyPassword = appProperties.getProperty("keystore.pass")
		}
	}
	
	buildTypes {
		debug {
			isDebuggable = true
			isShrinkResources = false
			isMinifyEnabled = false
			signingConfig = signingConfigs.getByName(getCurrentFlavor())
		}
		release {
			isDebuggable = false
			isShrinkResources = true
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
			)
			signingConfig = signingConfigs.getByName(getCurrentFlavor())
		}
	}
	flavorDimensions += "environment"
	productFlavors {
		fun loadConfig(flavor: ApplicationProductFlavor) {
			println("App ------ productFlavors: -----> ${flavor.name}")
			val appProperties = Properties().apply {
				load(FileInputStream(rootProject.file("env.${flavor.name}.properties")))
			}
			println("application.id: -----> " + appProperties.getProperty("application.id"))
			println("application.name: -----> " + appProperties.getProperty("application.name"))
			flavor.applicationId = appProperties.getProperty("application.id")
			flavor.manifestPlaceholders["applicationName"] =
				appProperties.getProperty("application.name")
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
		// Include plugin id 'kotlin-kapt' if enable dataBinding
		dataBinding = true
		viewBinding = true
		buildConfig = true
	}
}

dependencies {
	implementation(project(":domain"))
	implementation(project(":data"))
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.core.splashscreen)
	implementation(libs.androidx.swiperefreshlayout)
	implementation(libs.androidx.paging.runtime)
	implementation(libs.androidx.navigation.fragment.ktx)
	implementation(libs.androidx.navigation.ui.ktx)
	implementation(libs.androidx.lifecycle.viewmodel)
	implementation(libs.androidx.coroutines)
	testImplementation(libs.androidx.coroutines.test)
	implementation(libs.androidx.hilt)
	ksp(libs.androidx.hilt.compiler)
	
	implementation(libs.google.material)
	implementation(platform(libs.google.firebase.bom))
	implementation(libs.google.firebase.analytics)
	implementation(libs.google.gson)
	implementation(libs.load.images)
	implementation(libs.logger.timber)
	
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}
