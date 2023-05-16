plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Apps.javaCompileOption
    targetCompatibility = Apps.javaCompileOption
}
dependencies {
    api(project(":domain"))
    api("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("javax.inject:javax.inject:1")
}