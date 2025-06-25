package com.runtimeenabled.api

import androidx.privacysandbox.activity.core.SdkActivityLauncher
import androidx.privacysandbox.tools.PrivacySandboxService

@PrivacySandboxService
interface MyReSdkService {

    suspend fun initialize()

    suspend fun showFullscreenUi(activityLauncher: SdkActivityLauncher)

    suspend fun getRemoteUiAdapter(
        request: RemoteUiRequest,
        callback: RemoteUiCallbackInterface
    ): SdkSandboxedUiAdapter

    suspend fun createFile(sizeInMb: Long): String
}
