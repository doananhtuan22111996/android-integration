import com.android.build.api.dsl.ApplicationProductFlavor
import java.io.FileInputStream
import java.util.Properties
import java.util.regex.Matcher
import java.util.regex.Pattern

plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.androidHilt)
	alias(libs.plugins.ksp)
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
	namespace = "vn.root.app_compose"
	compileSdk = libs.versions.compileSdk.get().toInt()
	
	defaultConfig {
		applicationId = "vn.root.app_compose"
		minSdk = libs.versions.minSdk.get().toInt()
		targetSdk = libs.versions.targetSdk.get().toInt()
		versionCode = libs.versions.versionCode.get().toInt()
		versionName = libs.versions.versionName.get()
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}
	
	signingConfigs {
		create(getCurrentFlavor()) {
			// Load keystore
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
		}
		release {
			isDebuggable = false
			isShrinkResources = true
			isMinifyEnabled = true
			enableUnitTestCoverage = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
			)
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
// TODO conflict with MDC-Android application_id. Don't care about that, just use default
//			flavor.applicationId = appProperties.getProperty("application.id")
//			flavor.manifestPlaceholders["applicationName"] =
//				appProperties.getProperty("application.name")
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
		compose = true
	}
	
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.14"
	}
	
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	implementation(project(":domain"))
	implementation(project(":data"))

	implementation(libs.core.libx.domain)
	implementation(libs.core.libx.ui.composex)
	implementation(libs.core.libx.ui.mdc)

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.material3.icons)
	implementation(libs.androidx.ui.text.google.fonts)
	implementation(libs.androidx.core.splashscreen)
	implementation(libs.androidx.navigation.compose)
	implementation(libs.androidx.window.size)
	implementation(libs.androidx.viewmodel.compose)
	implementation(libs.androidx.paging.compose)
	implementation(libs.androidx.hilt)
	implementation(libs.androidx.navigation.hilt.compose)
	implementation(libs.androidx.coroutines)
	ksp(libs.androidx.hilt.compiler)
	
	testImplementation(libs.junit)
	testImplementation(libs.androidx.coroutines.test)
	testImplementation(libs.mockito)
	testImplementation(libs.mockito.kotlin)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	
	implementation(libs.logger.timber)
}
