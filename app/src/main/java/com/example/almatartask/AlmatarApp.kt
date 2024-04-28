package com.example.almatartask

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlmatarApp : Application() {
    init {
        application = this
    }

    companion object {
        private lateinit var application: AlmatarApp
        fun getContext(): Context = application.applicationContext
    }
}