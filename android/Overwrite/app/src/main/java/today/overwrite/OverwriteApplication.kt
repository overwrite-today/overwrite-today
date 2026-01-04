package today.overwrite

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class OverwriteApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 디버그 모드에서만 로그 출력
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Tink 초기화 (암호화)
        try {
            com.google.crypto.tink.aead.AeadConfig.register()
        } catch (e: Exception) {
            Timber.e(e, "Failed to initialize Tink")
        }
    }
}