package serio.tim.android.com.tastedive.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import serio.tim.android.com.tastedive.retrofit.Result
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val repository: MainRepository, val arrayList: ArrayList<Result>, val outcomeObservable: MutableLiveData<Outcome<ArrayList<Result>>>): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository, arrayList, outcomeObservable) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}