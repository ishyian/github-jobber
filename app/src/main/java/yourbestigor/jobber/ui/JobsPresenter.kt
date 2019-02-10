package yourbestigor.jobber.ui

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import yourbestigor.jobber.di.component.DaggerPresenterInjector
import yourbestigor.jobber.di.component.PresenterInjector
import yourbestigor.jobber.di.module.ContextModule
import yourbestigor.jobber.di.module.NetworkModule
import yourbestigor.jobber.network.GithubJobsApi
import javax.inject.Inject


@InjectViewState
class JobsPresenter: MvpPresenter<JobsView>() {

    @Inject
    lateinit var jobsApi: GithubJobsApi

    private var subscription: Disposable? = null

    fun onViewCreated() {
        loadJobs()
    }

    fun loadJobs() {
        viewState.showLoading()
        subscription = jobsApi
            .getJobs("ruby", "new york")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { viewState.hideLoading() }
            .subscribe(
                { jobsList -> viewState.updateJobs(jobsList) },
                { viewState.showError("Unknowm error") }
            )
    }

    fun onViewDestroyed() {
        subscription?.dispose()
    }

    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(viewState)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }


    private fun inject() {
        injector.inject(this)
    }

}