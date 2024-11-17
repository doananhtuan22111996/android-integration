import com.android.build.api.dsl.ApplicationProductFlavor
import vn.core.buildsrc.ConfigLoader
import vn.core.buildsrc.Configs
import vn.core.buildsrc.getCurrentFlavor
import java.io.FileInputStream
import java.util.Properties

plugins {
    vn.core.plugins.androidApplication
    alias(mobilex.plugins.googleService)
    alias(mobilex.plugins.firebaseCrashlytics)
}

android {
    namespace = Configs.MainApp.namespace

    defaultConfig {
        versionCode = Configs.MainApp.versionCode
        versionName = Configs.MainApp.versionName
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
}

dependencies {
    implementation(project(BuildModules.domain))
    implementation(project(BuildModules.data))
    implementation(libs.coreApp)
    implementation(libs.coreDomain)
    implementation(libs.coreMdc)
    implementation(mobilex.loadImages)
    implementation(mobilex.loggerTimber)
}
