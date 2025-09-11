// filepath: c:\Users\elouan\premiumcasts\app\src\main\java\au\com\shiftyjelly\pocketcasts\premium\PremiumUnlocker.kt
package au.com.shiftyjelly.pocketcasts.premium

import au.com.shiftyjelly.pocketcasts.models.type.Subscription
import au.com.shiftyjelly.pocketcasts.repositories.user.UserManager
import timber.log.Timber

object PremiumUnlocker {
    fun unlockPremium(userManager: UserManager) {
        try {
            // Directly set premium subscription bypassing payment
            userManager.setSubscription(Subscription.PatronPreview)
            Timber.i("Premium unlocked using fake payment method.")
        } catch (e: Exception) {
            Timber.e(e, "Failed to unlock premium.")
        }
    }
}
