import vn.core.buildsrc.Configs

plugins {
    vn.core.plugins.androidLibrary
}

android {
    namespace = Configs.Domain.namespace
}

dependencies {
    implementation(libs.coreDomain)
}