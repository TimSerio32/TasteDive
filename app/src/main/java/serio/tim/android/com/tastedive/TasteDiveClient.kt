package serio.tim.android.com.tastedive

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TasteDiveClient {
    @GET("similar")
    fun getSimilarData(@QueryMap map: Map<String, String>): Single<TasteDive>
}