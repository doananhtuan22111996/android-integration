package vn.core.buildsrc

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties
import java.util.regex.Matcher
import java.util.regex.Pattern

object ConfigLoader {
    fun load(project: Project, flName: String): Properties {
        return Properties().apply {
            load(FileInputStream(project.rootProject.file("env.$flName.properties")))
        }
    }
}