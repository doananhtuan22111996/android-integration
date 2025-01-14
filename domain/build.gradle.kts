import vn.core.buildsrc.Configs

plugins {
    vn.core.plugins.androidLibrary
}

android {
    namespace = Configs.Domain.NAMESPACE
}

dependencies {
    implementation(libs.coreDomain)
}