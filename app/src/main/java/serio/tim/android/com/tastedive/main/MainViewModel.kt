package serio.tim.android.com.tastedive.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import serio.tim.android.com.tastedive.retrofit.Result
import serio.tim.android.com.tastedive.retrofit.TasteDive
import javax.inject.Inject

class MainViewModel @Inject constructor(val repository: MainRepository, val arrayList: ArrayList<Result>, val outcomeObservable: MutableLiveData<Outcome<ArrayList<Result>>>): ViewModel() {
    fun getOutcomeObservable(): LiveData<Outcome<ArrayList<Result>>> = outcomeObservable

    fun getSimilarData(map: LinkedHashMap<String, String>) {
        outcomeObservable.value = Outcome.loading(true)
        repository.getSimilarData(map)!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<TasteDive?>() {
            override fun onSuccess(t: TasteDive) {
                val results = t.Similar.Results
                if (results.isEmpty()) {
                    outcomeObservable.value = Outcome.failure(Throwable("No results"))
                } else {
                    for (result in results) {
                        arrayList.add(result)
                    }
                    outcomeObservable.value = Outcome.success(arrayList)
                }
            }

            override fun onError(e: Throwable) {
                outcomeObservable.value = Outcome.failure(e)
            }
        })
    }
}