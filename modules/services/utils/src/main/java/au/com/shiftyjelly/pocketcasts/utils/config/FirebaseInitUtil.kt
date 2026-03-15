package au.com.shiftyjelly.pocketcasts.utils.config

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

/**
 * Ensures Firebase is initialized before accessing Firebase services.
 *
 * This is necessary because the google-services plugin is disabled, so Firebase
 * is not automatically initialized. Startup initializers (via ContentProviders)
 * may trigger Firebase dependency resolution before Application.onCreate() runs,
 * so we defensively initialize Firebase with dummy options when needed.
 */
object FirebaseInitUtil {
    fun ensureInitialized(context: Context) {
        if (FirebaseApp.getApps(context).isEmpty()) {
            val options = FirebaseOptions.Builder()
                .setApplicationId("1:1234567890:android:dummy")
                .setApiKey("dummy-api-key")
                .build()
            FirebaseApp.initializeApp(context, options)
        }
    }
}
