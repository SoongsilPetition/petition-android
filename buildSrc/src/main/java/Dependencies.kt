import org.gradle.api.JavaVersion

object Apps {
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33

    val javaCompileOption = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}

object Versions {
    const val kotlin = "1.8.20"
    const val compose = "1.4.3"
    const val room = "2.4.2"
}