import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import vn.core.buildsrc.Configs

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(mobilex.plugins.androidApplication) apply false
    alias(mobilex.plugins.androidLibrary) apply false
    alias(mobilex.plugins.kotlinAndroid) apply false
    alias(mobilex.plugins.jetbrainsKotlinJvm) apply false
    alias(mobilex.plugins.androidHilt) apply false
    alias(mobilex.plugins.androidRoom) apply false
    alias(mobilex.plugins.googleService) apply false
    alias(mobilex.plugins.firebaseCrashlytics) apply false
    alias(mobilex.plugins.ksp) apply false
}

tasks.register("generateTemplateProperties") {
    val str =
        "application.id=TODO application_id\n" + "application.name=TODO application_name\n" + "main.domain=TODO main_domain\n" + "keystore.path=TODO keystore_path (../path)\n" + "keystore.pass=TODO keystore_pass\n" + "keystore.alias=TODO keystore_alias"
    val devFile = rootProject.file("env.dev.properties")
    val prodFile = rootProject.file("env.prod.properties")
    try {
        if (!devFile.exists()) {
            devFile.createNewFile()
            devFile.writeText(str)
            println("Generated template dev properties file")
        }
        if (!prodFile.exists()) {
            prodFile.createNewFile()
            prodFile.writeText(str)
            println("Generated template prod properties file")
        }
    } catch (e: Exception) {
        println("Generated properties file error: ${e.message}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = Configs.jvmTarget
    setDependsOn(listOf(tasks.named("generateTemplateProperties")))
}
