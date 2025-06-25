package com.runtimeenabled.api

import androidx.privacysandbox.tools.PrivacySandboxCallback

/**
 * Interface to be implemented by runtime-aware SDK.
 *
 * Runtime-aware SDK will create an object that implements this interface and pass it to the
 * runtime-enabled SDK.
 *
 * This interface will then be used by the runtime-enabled SDK to communicate with the client app.
 */
@PrivacySandboxCallback
interface RemoteUiCallbackInterface {
    /** Function that is called on when the user wants to do something. */
    suspend fun onDoSomething()
}