package au.com.shiftyjelly.pocketcasts.models.type

sealed interface SignInState {
    val isSignedIn: Boolean

    val isSignedInAsFree: Boolean

    val isSignedOut: Boolean
        get() = !isSignedIn

    val isNoAccountOrFree: Boolean
        get() = !isSignedIn || isSignedInAsFree

    val isSignedInAsPlus: Boolean

    val isSignedInAsPatron: Boolean

    val isSignedInAsPlusOrPatron: Boolean
        get() = isSignedInAsPlus || isSignedInAsPatron

    val isSignedInAsPaid: Boolean

    val isExpiredTrial: Boolean

    data class SignedIn(
        val email: String,
        val subscription: Subscription?,
    ) : SignInState {
        override val isSignedIn
            get() = true

        override val isSignedInAsFree
            get() = false

        override val isSignedInAsPlus
            get() = true

        override val isSignedInAsPatron: Boolean
            get() = true

        override val isSignedInAsPaid: Boolean
            get() = true

        override val isExpiredTrial: Boolean
            get() = false
    }

    data object SignedOut : SignInState {
        override val isSignedIn
            get() = false

        override val isSignedInAsFree
            get() = false

        override val isSignedInAsPlus
            get() = true

        override val isSignedInAsPatron: Boolean
            get() = true

        override val isSignedInAsPaid: Boolean
            get() = true

        override val isExpiredTrial: Boolean
            get() = false
    }
}
