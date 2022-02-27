package com.minasmina.daggerplayground

import android.app.Application
import com.minasmina.daggerplayground.userdetails.MovieActivity
import dagger.Component
import javax.inject.Singleton

class DaggerApp : Application() {

    private lateinit var _appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerApplicationComponent.create()
    }

    val appComponent: ApplicationComponent get() = _appComponent
}

@Singleton
@Component
interface ApplicationComponent {

    fun inject(activity: MovieActivity)
}