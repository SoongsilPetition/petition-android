import org.gradle.api.JavaVersion

object Apps {
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33

    val javaCompileOption = JavaVersion.VERSION_1_8
    const val jvmTarget = "11"
}

object Versions {
    const val kotlin = "1.7.10"
    const val compose = "1.3.0-beta01"
    const val room = "2.4.2"
}