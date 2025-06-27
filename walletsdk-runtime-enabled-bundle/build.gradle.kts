plugins {
    id("com.android.privacy-sandbox-sdk")
}

android {
    compileSdk = 35
    minSdk = 26

    bundle {
        // The package name is used to load this SDK in the SDK Runtime.
        packageName = "com.michaeltchuang.walletsdk.runtimeenabled"
        setVersion(1, 0, 0)

        // Standard SDK Provider definition
        sdkProviderClassName = "androidx.privacysandbox.sdkruntime.provider.SandboxedSdkProviderAdapter"

        // This is your custom implementation of this specific SDK's provider,
        // an implementation of based on your definition of @PrivacySandboxService,
        // which defines your entry-point.
        compatSdkProviderClassName = "com.michaeltchuang.walletsdk.runtimeenabled.runtime.data.service.SdkProvider"
    }
}
dependencies {
    "include"(project(":walletsdk-runtime-enabled"))
}

