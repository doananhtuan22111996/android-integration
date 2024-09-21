package vn.core.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.util.Properties
import java.util.regex.Matcher
import java.util.regex.Pattern

fun Project.getLocalProperty(propertyName: String): String {
    val localProperties = Properties().apply {

        val localPropertiesFile = File(rootDir, "local.properties")
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName) ?: run {
        throw NoSuchFieldException("Not defined property: $propertyName")
    }
}

fun Project.repository() = uri("${Configs.mavenDomain}/${username()}/REPOSITORY")

fun Project.username() = System.getenv("USERNAME") ?: getLocalProperty("USERNAME")
fun Project.password() = System.getenv("TOKEN") ?: getLocalProperty("TOKEN")

fun Project.getCurrentFlavor(): String {
    val tskReqStr: String = gradle.startParameter.taskRequests.toString()
    println("Gradle task: -----> $tskReqStr")
    val pattern = if (tskReqStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else if (tskReqStr.contains("bundle")) {
        Pattern.compile("bundle(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("generate(\\w+)(Release|Debug)")
    }
    val matcher: Matcher = pattern.matcher(tskReqStr)
    if (matcher.find()) {
        println("MATCHED -------> ${matcher.group(1).lowercase()}")
        return matcher.group(1).lowercase()
    } else {
        println("NO MATCH FOUND ------> Return default flavor")
        return "dev"
    }
}