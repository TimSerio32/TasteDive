package serio.tim.android.com.tastedive.di

import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import serio.tim.android.com.tastedive.main.MainRepository
import serio.tim.android.com.tastedive.main.Outcome
import serio.tim.android.com.tastedive.retrofit.Result
import javax.inject.Singleton

@Module
class MainViewModelModule {
    @Provides
    @Singleton
    fun repository(retrofit: Retrofit): MainRepository {
        return MainRepository(retrofit)
    }

    @Provides
    @Singleton
    fun arrayList(): ArrayList<Result> {
        return ArrayList()
    }

    @Provides
    @Singleton
    fun outcomeObservable(): MutableLiveData<Outcome<ArrayList<Result>>> {
        return MutableLiveData<Outcome<ArrayList<Result>>>()
    }
}