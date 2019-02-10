package yourbestigor.jobber.ui

import yourbestigor.jobber.base.BaseView
import yourbestigor.jobber.model.Job

interface JobsView: BaseView {


    fun updateJobs(jobs: List<Job>)

    fun showError(error: String)

    fun showLoading()

    fun hideLoading()

}