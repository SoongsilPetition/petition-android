plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    mavenCentral()
    google()
}

java {
    sourceCompatibility = Apps.javaCompileOption
    targetCompatibility = Apps.javaCompileOption
}

dependencies {
    api(project(":entity"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("javax.inject:javax.inject:${Versions.javax_inject}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines_core}")

    // alternatively - without Android dependencies for tests
    implementation("androidx.paging:paging-common:3.1.1")
}