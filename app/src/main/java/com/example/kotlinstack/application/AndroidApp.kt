package com.example.kotlinstack.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidApp : Application() {
    // Hilt as DI See: https://github.com/android/architecture-samples/blob/dev-hilt for examples or follow codelabs
}