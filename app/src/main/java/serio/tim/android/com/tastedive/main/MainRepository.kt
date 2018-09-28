package serio.tim.android.com.tastedive.main

import io.reactivex.Single
import retrofit2.Retrofit
import serio.tim.android.com.tastedive.retrofit.TasteDive
import serio.tim.android.com.tastedive.retrofit.TasteDiveClient
import java.util.LinkedHashMap
import javax.inject.Inject

class MainRepository @Inject constructor(val retrofit: Retrofit) {

    fun getSimilarData(map: LinkedHashMap<String, String>): Single<TasteDive>? {
        return retrofit.create(TasteDiveClient::class.java).getSimilarData(map)
    }
}