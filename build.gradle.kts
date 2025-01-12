import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import vn.core.buildsrc.Configs

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(mobilex.plugins.composeCompiler) apply false
    alias(mobilex.plugins.androidHilt) apply false
    alias(mobilex.plugins.androidRoom) apply false
    alias(mobilex.plugins.googleService) apply false
    alias(mobilex.plugins.firebaseCrashlytics) apply false
    alias(mobilex.plugins.spotless) apply false
}

tasks.register("generateTemplateProperties") {
    val str =
        "application.id=TODO application_id\n" + "application.name=TODO application_name\n" + "main.domain=TODO main_domain"
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

allprojects {
    apply {
        plugin(rootProject.mobilex.plugins.spotless.get().pluginId)
    }

    configure<SpotlessExtension> {
        // Configuration for Java files
        java {
            target("**/*.java")
            googleJavaFormat().aosp() // Use Android Open Source Project style
            removeUnusedImports() // Automatically remove unused imports
            trimTrailingWhitespace() // Remove trailing whitespace
        }

        // Configuration for Kotlin files
        kotlin {
            target("**/*.kt")
            targetExclude("${layout.buildDirectory}/**/*.kt") // Exclude files in the build directory
            ktlint("1.5.0").setEditorConfigPath(rootProject.file(".editorconfig").path) // Use ktlint with version 1.2.1 and custom .editorconfig
            toggleOffOn() // Allow toggling Spotless off and on within code files using comments
            trimTrailingWhitespace()
        }

        // Additional configuration for Kotlin Gradle scripts
        kotlinGradle {
            target("*.gradle.kts")
            ktlint("1.5.0") // Apply ktlint to Gradle Kotlin scripts
        }
    }
}
