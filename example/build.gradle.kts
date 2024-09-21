import vn.core.buildsrc.Configs

plugins {
    `java-library`
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Configs.javaVersion
    targetCompatibility = Configs.javaVersion
}

dependencies {
    implementation(mobilex.androidxCoroutines)
}