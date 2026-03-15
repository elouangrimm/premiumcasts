package au.com.shiftyjelly.pocketcasts.utils.di

import android.content.Context
import au.com.shiftyjelly.pocketcasts.utils.config.FirebaseConfig
import au.com.shiftyjelly.pocketcasts.utils.config.FirebaseInitUtil
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
        FirebaseInitUtil.ensureInitialized(context)
        return FirebaseRemoteConfig.getInstance().apply {
            val config = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(2L * 60L * 60L)
                .build()
            setConfigSettingsAsync(config)
            setDefaultsAsync(FirebaseConfig.defaults)
        }
    }
}
