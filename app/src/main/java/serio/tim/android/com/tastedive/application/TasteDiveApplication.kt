package serio.tim.android.com.tastedive.application

import android.app.Application
import serio.tim.android.com.tastedive.di.AppComponent
import serio.tim.android.com.tastedive.di.AppModule
import serio.tim.android.com.tastedive.di.DaggerAppComponent

class TasteDiveApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: TasteDiveApplication): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
    }
}