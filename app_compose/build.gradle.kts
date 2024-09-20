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
    alias(mobilex.plugins.ksp)
}

android {
    namespace = Configs.MainAppCompose.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.MainAppCompose.namespace
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.MainAppCompose.versionCode
        versionName = Configs.MainAppCompose.versionName

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
            val appProperties = ConfigLoader.load(project, flavor.name)
            println("App ------ productFlavors: -----> ${flavor.name}")
            println("application.id: -----> " + appProperties.getProperty("application.id"))
            println("application.name: -----> " + appProperties.getProperty("application.name"))
            // TODO
            // flavor.applicationId = appProperties.getProperty("application.id")
            // flavor.manifestPlaceholders["applicationName"] =
            //    appProperties.getProperty("application.name")
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

    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }

    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Configs.MainAppCompose.kotlinCompilerExtensionVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(BuildModules.domain))
    implementation(project(BuildModules.data))

    implementation(mobilex.coreLibxDomain)
    implementation(mobilex.coreLibxUiComposex)
    implementation(mobilex.coreLibxUiMdc)
    implementation(mobilex.androidxCoreKtx)
    implementation(mobilex.androidxCoreSplashscreen)
    implementation(platform(mobilex.androidxComposeBom))
    implementation(mobilex.bundles.jetpackComposeComponents)
    implementation(mobilex.androidxCoroutines)
    implementation(mobilex.bundles.lifecycleComponents)
    implementation(mobilex.androidxHilt)
    ksp(mobilex.androidxHiltCompiler)
    implementation(mobilex.loggerTimber)

    testImplementation(mobilex.bundles.testComponents)
    androidTestImplementation(mobilex.bundles.androidTestComponents)
    debugImplementation(mobilex.androidxComposeUiTooling)
    debugImplementation(mobilex.androidxComposeUiTestManifest)
}
