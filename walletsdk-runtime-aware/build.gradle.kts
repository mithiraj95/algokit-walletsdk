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

    // --- DataStore ---
    implementation(libs.androidx.datastore.preferences)

    // --- Coroutines ---
    implementation(libs.bundles.coroutines)

    // --- Utility & Other ---
    implementation(libs.accompanist.permissions)
    implementation(libs.converter.gson)
    implementation(libs.qr.kit)

    // ---webview
    implementation(libs.compose.webview.multiplatform)
    implementation(libs.webview.multiplatform.mobile)

    api(libs.algosdk)
    implementation(libs.kotlin.stdlib.jdk8)
    // toml files don't support aar files yet
    implementation("net.java.dev.jna:jna:5.17.0@aar")
    implementation(libs.xhdwalletapi)
    implementation(libs.kotlin.bip39)
    implementation(libs.koin.android)
}

room {
    schemaDirectory("$projectDir/schemas")
}
