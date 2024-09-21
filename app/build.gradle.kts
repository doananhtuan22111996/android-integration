import com.android.build.api.dsl.ApplicationProductFlavor
import vn.core.buildsrc.ConfigLoader
import vn.core.buildsrc.Configs
import vn.core.buildsrc.getCurrentFlavor
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(mobilex.plugins.androidApplication)
    alias(mobilex.plugins.kotlinAndroid)
    alias(mobilex.plugins.androidHilt)
    alias(mobilex.plugins.googleService)
    alias(mobilex.plugins.firebaseCrashlytics)
    id("kotlin-kapt")
}

android {

    namespace = Configs.MainApp.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.MainApp.versionCode
        versionName = Configs.MainApp.versionName
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
            val appProperties = ConfigLoader.load(project, flavor.name)
            println("App ------ productFlavors: -----> ${flavor.name}")
            println("application.id: -----> " + appProperties.getProperty("application.id"))
            println("application.name: -----> " + appProperties.getProperty("application.name"))
            flavor.applicationId = appProperties.getProperty("application.id")
            flavor.manifestPlaceholders["applicationName"] =
                appProperties.getProperty("application.name")
        }

        create(Configs.Flavor.dev) {
            dimension = "environment"
            loadConfig(this)
        }

        create(Configs.Flavor.prod) {
            dimension = "environment"
            loadConfig(this)
        }
    }

    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }

    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }

    buildFeatures {
        // Include plugin id 'kotlin-kapt' if enable dataBinding
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(BuildModules.domain))
    implementation(project(BuildModules.data))

    implementation(mobilex.coreLibxDomain)
    implementation(mobilex.coreLibxUiBase)
    implementation(mobilex.coreLibxUiMdc)
    implementation(mobilex.bundles.coreAndroidComponents)
    kapt(mobilex.androidxHiltCompiler)
    implementation(platform(mobilex.firebaseBom))
    implementation(mobilex.bundles.firebaseComponents)
    implementation(mobilex.googleGson)
    implementation(mobilex.loadImages)
    implementation(mobilex.loggerTimber)

    testImplementation(mobilex.bundles.testComponents)
    testImplementation(mobilex.bundles.androidTestComponents)
    kaptTest(mobilex.androidxHiltTesting)
    kaptAndroidTest(mobilex.androidxHiltTesting)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}