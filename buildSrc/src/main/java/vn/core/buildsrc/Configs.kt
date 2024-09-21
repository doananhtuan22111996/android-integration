package vn.core.buildsrc

import org.gradle.api.JavaVersion

object Configs {
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    const val jvmTarget = "17"
    val javaVersion = JavaVersion.VERSION_17
    const val mavenDomain = "https://maven.pkg.github.com"

    object Flavor {
        const val dev = "dev"
        const val prod = "prod"
    }

    object MainApp {
        const val namespace = "vn.main.app"
        const val versionCode = 1
        const val versionName = "4.1.0"
    }

    object MainAppCompose {
        const val namespace = "vn.main.appCompose"
        const val versionCode = 1
        const val versionName = "1.0.0"
        const val kotlinCompilerExtensionVersion = "1.5.14"
    }

    object Data {
        const val namespace = "vn.main.data"
    }

    object Domain {
        const val namespace = "vn.main.domain"
    }
}


