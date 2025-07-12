plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    privacySandbox {
        enable = true
    }

    defaultConfig {
        applicationId = "com.michaeltchuang.wallet"
        compileSdk = 35
        minSdk = 28
        versionCode = 3
        versionName = "0.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    namespace = "com.michaeltchuang.wallet"
}

dependencies {

    implementation(project(":walletsdk-runtime-aware"))
    implementation(libs.androidx.activity.compose)
    // Correctly apply the Compose BOM as a platform
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.bundles.sdkruntimeUI)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.lottie.compose)
    implementation(libs.koin.androidx.compose) // or latest
    implementation(libs.androidx.navigation.compose)

    implementation(libs.koin.core)
    implementation(libs.koin.core.viewmodel)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.kotlinx.serialization.json)
}
