package com.runtimeenabled.api

import androidx.privacysandbox.tools.PrivacySandboxValue

@PrivacySandboxValue
data class RemoteUiRequest(
    /** Message from the client app. */
    val message: String
)
