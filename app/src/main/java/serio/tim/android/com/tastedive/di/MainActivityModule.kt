package serio.tim.android.com.tastedive.di

import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import serio.tim.android.com.tastedive.main.MainRepository
import serio.tim.android.com.tastedive.main.MainViewModelFactory
import serio.tim.android.com.tastedive.main.Outcome
import serio.tim.android.com.tastedive.retrofit.Result
import serio.tim.android.com.tastedive.main.TasteDiveAdapter
import javax.inject.Singleton

@Module
class MainActivityModule {
    @Provides
    @Singleton
    fun viewModelFactory(repository: MainRepository, arrayList: ArrayList<Result>, outcomeObservable: MutableLiveData<Outcome<ArrayList<Result>>>): MainViewModelFactory {
        return MainViewModelFactory(repository, arrayList, outcomeObservable)
    }

    @Provides
    @Singleton
    fun tasteDiveAdapter(): TasteDiveAdapter {
        return TasteDiveAdapter()
    }
}