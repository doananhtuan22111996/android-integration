import com.android.build.api.dsl.LibraryProductFlavor
import vn.core.buildsrc.ConfigLoader
import vn.core.buildsrc.Configs
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(mobilex.plugins.kotlinAndroid)
    alias(mobilex.plugins.androidLibrary)
    alias(mobilex.plugins.ksp)
    alias(mobilex.plugins.binaryCompatibilityValidator)
}

android {
    namespace = Configs.Data.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isJniDebuggable = false
            isMinifyEnabled = true
            enableUnitTestCoverage = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "environment"
    productFlavors {
        fun loadConfig(flavor: LibraryProductFlavor) {
            val appProperties = ConfigLoader.load(project, flavor.name)
            println("Data ----- productFlavors: -----> ${flavor.name}")
            println("main.domain: -----> " + appProperties.getProperty("main.domain"))
            flavor.buildConfigField(
                "String", "MAIN_DOMAIN", appProperties.getProperty("main.domain")
            )
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(BuildModules.domain))

    implementation(mobilex.coreLibxDomain)
    implementation(mobilex.coreLibxData)

    implementation(mobilex.bundles.pagingComponents)
    implementation(mobilex.androidxRoomRuntime)
    ksp(mobilex.androidxRoomCompiler)
    implementation(mobilex.androidxSecurity)
    implementation(mobilex.androidxHilt)
    ksp(mobilex.androidxHiltCompiler)
    implementation(mobilex.retrofit)
    implementation(mobilex.retrofitGson)
    implementation(mobilex.loggerTimber)
    implementation(mobilex.loggerOkhttp)

    testImplementation(mobilex.bundles.testComponents)
    androidTestImplementation(mobilex.bundles.androidTestComponents)
}
