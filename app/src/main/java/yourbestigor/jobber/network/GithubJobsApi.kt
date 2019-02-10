package yourbestigor.jobber.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import yourbestigor.jobber.model.Job

interface GithubJobsApi {

    @GET("/positions.json")
    fun getJobs(@Query("description") description: String,
                @Query("location") location: String)
            : Observable<List<Job>>

}