pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {
    repositories {
// Make sure that you have the following two repositories
        google() // Google's Maven repository
        mavenCentral() // Maven Central repository
    }
    dependencies {
// Add the dependency for the Google services Gradle plugin
        classpath ("com.google.gms:google-services:4.3.15")
    }
}

rootProject.name = "Bai1"
include(":app")
 