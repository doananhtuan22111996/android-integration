import com.android.build.api.dsl.ApplicationProductFlavor
import vn.core.buildsrc.ConfigLoader
import vn.core.buildsrc.Configs

plugins {
    vn.core.plugins.androidApplication
}

android {
    namespace = Configs.MainAppCompose.namespace

    defaultConfig {
        applicationId = Configs.MainAppCompose.namespace
        versionCode = Configs.MainAppCompose.versionCode
        versionName = Configs.MainAppCompose.versionName
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
}

dependencies {
    implementation(project(BuildModules.domain))
    implementation(project(BuildModules.data))
    implementation(libs.coreDomain)
    implementation(libs.coreCompose)
    implementation(libs.coreMdc)
}
