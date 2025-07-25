plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.michaeltchuang.walletsdk.runtimeaware"
    privacySandbox {
        enable = true
    }
    defaultConfig {
        compileSdk = 35
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    // --- Module dependencies ---
    debugImplementation(project(":walletsdk-runtime-enabled-bundle"))

    // --- Privacy Sandbox + SDK Runtime ---
    implementation(libs.bundles.sdkruntimeRASDK)

    // --- AndroidX & Compose UI ---
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.tooling.preview)

    // --- Dependency Injection ---
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // --- Room/Database ---
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // --- Utility & Other ---
    implementation(libs.accompanist.permissions)
    implementation(libs.converter.gson)
    implementation(libs.qr.kit)
}

room {
    schemaDirectory("$projectDir/schemas")
}
