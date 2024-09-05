import com.android.build.api.dsl.ApplicationProductFlavor
import java.io.FileInputStream
import java.util.Properties
import java.util.regex.Matcher
import java.util.regex.Pattern

plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.androidHilt)
	id("kotlin-kapt")
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
			enableUnitTestCoverage = true
			signingConfig = signingConfigs.getByName(getCurrentFlavor())
		}
		release {
			isDebuggable = false
			isShrinkResources = true
			isMinifyEnabled = true
			enableUnitTestCoverage = true
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
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	
	kotlinOptions {
		jvmTarget = "17"
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

	implementation(libs.core.libx.domain)
	implementation(libs.core.libx.ui.base)
	implementation(libs.core.libx.ui.mdc)

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.appcompat.resource)
	implementation(libs.androidx.core.splashscreen)
	implementation(libs.androidx.swiperefreshlayout)
	implementation(libs.androidx.paging.runtime)
	implementation(libs.androidx.navigation.fragment.ktx)
	implementation(libs.androidx.navigation.ui.ktx)
	implementation(libs.androidx.lifecycle.viewmodel)
	implementation(libs.androidx.coroutines)
	implementation(libs.androidx.hilt)
	kapt(libs.androidx.hilt.compiler)

	implementation(libs.google.material)
	implementation(platform(libs.google.firebase.bom))
	implementation(libs.google.firebase.analytics)
	implementation(libs.google.gson)
	implementation(libs.load.images)
	implementation(libs.logger.timber)

	testImplementation(libs.androidx.coroutines.test)
	testImplementation(libs.androidx.hilt.testing)
	kaptTest(libs.androidx.hilt.testing)
	testImplementation(libs.junit)
	testImplementation(libs.mockito)
	testImplementation(libs.mockito.kotlin)
	androidTestImplementation(libs.androidx.hilt.testing)
	kaptAndroidTest(libs.androidx.hilt.testing)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}

// Allow references to generated code
kapt {
	correctErrorTypes = true
}