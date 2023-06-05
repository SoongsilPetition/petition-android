import org.gradle.api.JavaVersion

object Apps {
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33

    val javaCompileOption = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}

object Versions {
    const val gradle = "8.0.1"
    const val kotlin = "1.8.21"
    const val hilt_android_gradle_plugin = "2.44.2"
    const val google_services = "4.3.15"
    const val compose = "1.4.3"
    const val junit = "1.10.1"
    const val junit_test = "4.13.2"
    const val junit_android_test = "1.1.5"
    const val espresso_core = "3.5.1"
    const val security_crypto = "1.1.0-alpha06"
    const val lifecycle_runtime_ktx = "2.6.1"
    const val activity_compose = "1.7.2"
    const val lifecycle_viewmodel_compose = "2.6.1"
    const val hilt = "2.46.1"
    const val hilt_navigation_compose = "1.0.0"
    const val retrofit = "2.9.0"
    const val retrofit_converter_gson = "2.9.0"
    const val okhttp = "4.11.0"
    const val okhttp_urlconnection = "4.11.0"
    const val logging_interceptor = "4.11.0"
    const val timber = "5.0.1"
    const val gson = "2.10.1"
    const val javax_inject = "1"
    const val kotlinx_coroutines_core = "1.7.1"
    const val compose_bom = "2023.05.01"
}