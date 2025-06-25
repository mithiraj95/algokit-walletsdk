plugins {
    id("androidx.privacysandbox.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.runtimeenabled"

    defaultConfig {
        minSdk = 21
        compileSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // consumerProguardFiles += "consumer-rules.pro"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    implementation(libs.androidx.activity.ktx)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.sdkruntimeActivity)
    implementation(libs.bundles.sdkruntimeBackcompat)
    implementation(libs.bundles.sdkruntimeUI)
    implementation(libs.sdkruntime.tools.tools)
    ksp(libs.androidx.annotation)
    ksp(libs.sdkruntime.tools.apicompiler)
}
