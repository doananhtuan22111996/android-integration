package vn.core.buildsrc

import org.gradle.api.JavaVersion

object Configs {
     val jvmTarget = JavaVersion.VERSION_11.toString()
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


