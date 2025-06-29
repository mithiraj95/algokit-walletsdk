plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.michaeltchuang.walletsdk.runtimeaware"
    privacySandbox {
        enable = true
    }
    defaultConfig {
        compileSdk = 35
        minSdk = 26
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
    debugImplementation(project(":walletsdk-runtime-enabled-bundle"))
    // Note that here we depend on the bundle modules, not the sdk modules.
    // While the libraries contain the SDK's logic, they lack the shim generated classes,
    // and apps won't compile using its full classpath.
    // Instead, the bundle contains information the SDK's API,
    // and the AGP Plugin used to generate sources and compile the app.
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.sdkruntimeRASDK)
    implementation(libs.androidx.activity.compose)
    // Correctly apply the Compose BOM as a platform
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
}
