plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.runtimeaware.sdk"
    privacySandbox {
        enable = true
    }
    defaultConfig {
        compileSdk = 35
        minSdk = 21
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
    // Note that here we depend on the bundle modules, not the sdk modules.
    // While the libraries contain the SDK's logic, they lack the shim generated classes,
    // and apps won't compile using its full classpath.
    // Instead, the bundle contains information the SDK's API,
    // and the AGP Plugin used to generate sources and compile the app.
    debugImplementation(project(":runtime-enabled-sdk-bundle"))
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.sdkruntimeRASDK)
}