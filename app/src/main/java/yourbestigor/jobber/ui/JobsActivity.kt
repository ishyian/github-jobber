package yourbestigor.jobber.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import yourbestigor.jobber.R
import yourbestigor.jobber.databinding.ActivityJobsBinding
import yourbestigor.jobber.model.Job
import yourbestigor.jobber.util.androidx.MvpAppCompatActivity





class JobsActivity : MvpAppCompatActivity(), JobsView {

    private lateinit var binding: ActivityJobsBinding
    private lateinit var adapter: JobsAdapter

    @InjectPresenter
    lateinit var presenter: JobsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs)
        adapter = JobsAdapter(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_jobs)
        binding.adapter = adapter
        binding.jobs.layoutManager = LinearLayoutManager(this)

        hideLoading()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updateJobs(jobs: List<Job>) {
        adapter.updateJobs(jobs)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressBar.bringToFront()
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.jobs_menu, menu)
        val search = menu!!.findItem(R.id.action_search)

        val searchView = search.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.loadJobs(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true
    }

}
