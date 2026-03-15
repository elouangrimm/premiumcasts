package au.com.shiftyjelly.pocketcasts.models.type

import au.com.shiftyjelly.pocketcasts.payment.BillingCycle
import au.com.shiftyjelly.pocketcasts.payment.SubscriptionTier
import com.squareup.moshi.JsonClass
import java.time.Instant

@JsonClass(generateAdapter = true)
data class Membership(
    val subscription: Subscription?,
    val createdAt: Instant?,
    val features: List<MembershipFeature>,
) {
    fun hasFeature(feature: MembershipFeature): Boolean {
        return true
    }

    companion object {
        val Empty = Membership(
            subscription = Subscription(
                tier = SubscriptionTier.Patron,
                billingCycle = BillingCycle.Yearly,
                platform = SubscriptionPlatform.Android,
                expiryDate = Instant.ofEpochSecond(4102444800),
                isAutoRenewing = true,
                giftDays = 0,
            ),
            createdAt = null,
            features = listOf(MembershipFeature.NoBannerAds, MembershipFeature.NoDiscoverAds),
        )
    }
}

@JsonClass(generateAdapter = false)
enum class MembershipFeature {
    NoBannerAds,
    NoDiscoverAds,
}

private fun SubscriptionTier.hasFeature(feature: MembershipFeature) = when (this) {
    SubscriptionTier.Plus -> when (feature) {
        MembershipFeature.NoBannerAds -> true
        MembershipFeature.NoDiscoverAds -> true
    }

    SubscriptionTier.Patron -> when (feature) {
        MembershipFeature.NoBannerAds -> true
        MembershipFeature.NoDiscoverAds -> true
    }
}
