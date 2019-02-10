package yourbestigor.jobber.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import yourbestigor.jobber.network.GithubJobsApi

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideJobsApi(retrofit: Retrofit): GithubJobsApi {
        return retrofit.create(GithubJobsApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jobs.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}