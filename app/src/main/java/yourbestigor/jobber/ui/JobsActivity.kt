package yourbestigor.jobber.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import yourbestigor.jobber.R
import yourbestigor.jobber.base.BaseActivity
import yourbestigor.jobber.databinding.ActivityJobsBinding
import yourbestigor.jobber.model.Job

class JobsActivity : BaseActivity<JobsPresenter>(), JobsView {

    private lateinit var binding: ActivityJobsBinding
    private lateinit var adapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs)
        adapter = JobsAdapter(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_jobs)
        binding.adapter = adapter
        binding.jobs.layoutManager = LinearLayoutManager(this)

        presenter.onViewCreated()

    }


    override fun instantiatePresenter(): JobsPresenter {
        return JobsPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updateJobs(jobs: List<Job>) {
        adapter.updatePosts(jobs)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.INVISIBLE
    }
}
