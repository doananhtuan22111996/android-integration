import vn.core.buildsrc.Configs

plugins {
    alias(mobilex.plugins.kotlinAndroid)
    alias(mobilex.plugins.androidLibrary)
}

android {
    namespace = Configs.Domain.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            enableUnitTestCoverage = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
}

dependencies {
    implementation(mobilex.coreLibxDomain)
    implementation(mobilex.androidxCoreCoroutines)
    implementation(mobilex.androidxPagingCommon)
    compileOnly(mobilex.javax)

    testImplementation(mobilex.bundles.testComponents)
    androidTestImplementation(mobilex.bundles.androidTestComponents)
}