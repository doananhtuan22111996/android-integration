import com.android.build.api.dsl.ApplicationProductFlavor
import vn.core.buildsrc.ConfigLoader
import vn.core.buildsrc.Configs

plugins {
    vn.core.plugins.androidApplication
    alias(mobilex.plugins.googleService)
    alias(mobilex.plugins.firebaseCrashlytics)
}

android {
    namespace = Configs.App.NAMESPACE_APP

    defaultConfig {
        applicationId = Configs.App.APPLICATION_APP_ID
        versionCode = Configs.App.VERSION_CODE
        versionName = Configs.App.VERSION_NAME
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

        create(Configs.Flavor.DEV) {
            dimension = "environment"
            loadConfig(this)
        }

        create(Configs.Flavor.PROD) {
            dimension = "environment"
            loadConfig(this)
        }
    }
}

dependencies {
    implementation(project(BuildModules.DOMAIN))
    implementation(project(BuildModules.DATA))
    implementation(libs.coreApp)
    implementation(libs.coreDomain)
    implementation(libs.coreMdc)
    implementation(mobilex.loadImages)
    implementation(mobilex.loggerTimber)
}
