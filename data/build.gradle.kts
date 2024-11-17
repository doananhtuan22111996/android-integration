import com.android.build.api.dsl.LibraryProductFlavor
import vn.core.buildsrc.ConfigLoader
import vn.core.buildsrc.Configs

plugins {
    vn.core.plugins.androidLibrary
}

android {
    namespace = Configs.Data.namespace

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
}

dependencies {
    implementation(project(BuildModules.domain))
    implementation(libs.coreDomain)
    implementation(libs.coreData)
}
