package vn.core.buildsrc

import org.gradle.api.JavaVersion

object Configs {
    val jvmTarget = JavaVersion.VERSION_11.toString()
    const val MAVEN_DOMAIN = "https://maven.pkg.github.com"

    object Flavor {
        const val DEV = "dev"
        const val PROD = "prod"
    }

    object App {
        const val APPLICATION_APP_ID = "com.feature.app"
        const val APPLICATION_COMPOSE_ID = "com.feature.app"
        const val NAMESPACE_APP = "com.feature.app"
        const val NAMESPACE_COMPOSE = "com.feature.compose"
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0.0"
    }

    object Data {
        const val NAMESPACE = "vn.main.data"
    }

    object Domain {
        const val NAMESPACE = "vn.main.domain"
    }
}
