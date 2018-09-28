package serio.tim.android.com.tastedive.di

import dagger.Component
import serio.tim.android.com.tastedive.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MainViewModelModule::class, MainActivityModule::class, MainRepositoryModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
}