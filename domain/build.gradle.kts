plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Apps.javaCompileOption
    targetCompatibility = Apps.javaCompileOption
}

dependencies {
    api(project(":entity"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
}