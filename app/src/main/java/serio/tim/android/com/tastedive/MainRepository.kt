package serio.tim.android.com.tastedive

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.LinkedHashMap

class MainRepository {

    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }

    fun getSimilarData(map: LinkedHashMap<String, String>): Single<TasteDive>? {
        return getRetrofit()?.create(TasteDiveClient::class.java)?.getSimilarData(map)
    }
}