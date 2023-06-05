plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Apps.compileSdk

    defaultConfig {
        applicationId = "com.marassu.petition"
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Apps.javaCompileOption
        targetCompatibility = Apps.javaCompileOption
    }
    kotlinOptions {
        jvmTarget = Apps.jvmTarget
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.marassu.petition"
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("androidx.core:core-ktx:${Versions.junit}")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.compose}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime_ktx}")
    implementation("androidx.activity:activity-compose:${Versions.activity_compose}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle_viewmodel_compose}")
    implementation("androidx.security:security-crypto:${Versions.security_crypto}")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:${Versions.compose_bom}"))
    testImplementation("junit:junit:${Versions.junit_test}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.junit_android_test}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso_core}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
    androidTestImplementation(platform("androidx.compose:compose-bom:${Versions.compose_bom}"))
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.compose}")

    // data
    implementation(project(":data"))

    // Hilt, Dagger
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_compose}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}")
    implementation("com.squareup.retrofit2:converter-scalars:${Versions.retrofit}")

    // okHttp
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttp}")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp_urlconnection}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}")

    // Logging
    implementation("com.jakewharton.timber:timber:${Versions.timber}")
}