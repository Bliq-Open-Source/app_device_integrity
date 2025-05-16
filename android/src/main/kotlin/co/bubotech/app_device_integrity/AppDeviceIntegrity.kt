package co.bubotech.app_device_integrity

import com.google.android.gms.tasks.Task
import com.google.android.play.core.integrity.IntegrityTokenResponse
import com.google.android.play.core.integrity.IntegrityManager
import com.google.android.play.core.integrity.IntegrityManagerFactory
import com.google.android.play.core.integrity.IntegrityTokenRequest
import android.content.Context
import android.util.Base64

class AppDeviceIntegrity(context: Context, cloudProjectNumber: Long, challengeString: String) {

    var nonce = Base64.encodeToString(
        challengeString.toByteArray(Charsets.UTF_8),
        Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
    )

    // Create an instance of a manager.
    val integrityManager: IntegrityManager = IntegrityManagerFactory.create(context)

    // Request the integrity token by providing a nonce.
    val integrityTokenResponse: Task<IntegrityTokenResponse> = integrityManager.requestIntegrityToken(
        IntegrityTokenRequest.builder()
            .setNonce(nonce)
            .setCloudProjectNumber(cloudProjectNumber)
            .build())

}