package yourbestigor.jobber.ui

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import yourbestigor.jobber.R
import yourbestigor.jobber.base.BasePresenter
import yourbestigor.jobber.network.GithubJobsApi
import javax.inject.Inject

class JobsPresenter(jobsView: JobsView): BasePresenter<JobsView>(jobsView) {

    @Inject
    lateinit var jobsApi: GithubJobsApi

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadJobs()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadJobs() {
        view.showLoading()
        subscription = jobsApi
            .getPosts("ruby", "new york")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { jobsList -> view.updateJobs(jobsList) },
                { view.showError(R.string.unknown_error) }
            )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }

}