plugins {
    id("androidx.privacysandbox.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.room)
}

android {
    namespace = "com.michaeltchuang.walletsdk.runtimeaware"

    defaultConfig {
        minSdk = 28
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
    sourceSets["main"].jniLibs.srcDirs("src/main/jniLibs")
}

dependencies {
    api(libs.algosdk)
   // api(libs.algorand.go.mobile)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.sdkruntimeActivity)
    implementation(libs.bundles.sdkruntimeBackcompat)
    implementation(libs.bundles.sdkruntimeUI)
    implementation(libs.sdkruntime.tools.tools)
    // toml files don't support aar files yet
    implementation("net.java.dev.jna:jna:5.17.0@aar")
    implementation(libs.xhdwalletapi)
    implementation(libs.kotlin.bip39)
    implementation(libs.koin.android)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.androidx.annotation)
    ksp(libs.sdkruntime.tools.apicompiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}