plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "today.overwrite"
    compileSdk = 36

    defaultConfig {
        applicationId = "today.overwrite"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "API_BASE_URL", "\"https://api.overwrite.today\"")
            buildConfigField("String", "WS_BASE_URL", "\"wss://api.overwrite.today/ws\"")
        }

        debug {
            applicationIdSuffix = ".debug"
            buildConfigField("String", "API_BASE_URL", "\"https://dev-api.overwrite.today\"")
            buildConfigField("String", "WS_BASE_URL", "\"wss://dev-api.overwrite.today/ws\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    // ========================================
    // ===== Core (기본) =====
    // ========================================
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // ========================================
    // ===== Compose UI =====
    // ========================================
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.compose.material.icons.extended)

    // ========================================
    // ===== ViewModel & Lifecycle =====
    // ========================================
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)

    // ========================================
    // ===== Navigation =====
    // ========================================
    implementation(libs.navigation.compose)

    // ========================================
    // ===== Hilt (의존성 주입) =====
    // ========================================
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // ========================================
    // ===== Room (로컬 DB) =====
    // ========================================
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // ========================================
    // ===== Network (Retrofit + OkHttp) =====
    // ========================================
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // ========================================
    // ===== WebSocket =====
    // ========================================
    implementation(libs.java.websocket)

    // ========================================
    // ===== Coroutines =====
    // ========================================
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    // ========================================
    // ===== DataStore =====
    // ========================================
    implementation(libs.datastore.preferences)

    // ========================================
    // ===== Encryption (Tink) =====
    // ========================================
    implementation(libs.tink.android)

    // ========================================
    // ===== Image Loading (Coil) =====
    // ========================================
    implementation(libs.coil.compose)

    // ========================================
    // ===== Accompanist =====
    // ========================================
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.permissions)

    // ========================================
    // ===== Testing =====
    // ========================================
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // ========================================
    // ===== Debug Tools =====
    // ========================================
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // 로깅
    implementation(libs.timber)
}