package au.com.shiftyjelly.pocketcasts.utils.di

import android.content.Context
import au.com.shiftyjelly.pocketcasts.utils.config.FirebaseConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(@ApplicationContext context: Context): FirebaseRemoteConfig {
        ensureFirebaseInitialized(context)
        return FirebaseRemoteConfig.getInstance().apply {
            val config = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(2L * 60L * 60L)
                .build()
            setConfigSettingsAsync(config)
            setDefaultsAsync(FirebaseConfig.defaults)
        }
    }

    private fun ensureFirebaseInitialized(context: Context) {
        if (FirebaseApp.getApps(context).isEmpty()) {
            val options = FirebaseOptions.Builder()
                .setApplicationId("1:1234567890:android:dummy")
                .setApiKey("dummy-api-key")
                .build()
            FirebaseApp.initializeApp(context, options)
        }
    }
}
