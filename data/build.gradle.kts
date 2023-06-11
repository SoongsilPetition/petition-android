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
    api(project(":domain"))
    api("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("javax.inject:javax.inject:${Versions.javax_inject}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines_core}")
    implementation("androidx.paging:paging-common-ktx:3.1.1")
    implementation("com.google.code.gson:gson:${Versions.gson}")
}