/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("com.calgarytransitapi.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":utilities"))
}

application {
    // Define the main class for the application.
    mainClass.set("com.calgarytransitapi.app.AppKt")
}
